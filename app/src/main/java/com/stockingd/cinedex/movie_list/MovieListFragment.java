package com.stockingd.cinedex.movie_list;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.stockingd.cinedex.app.BaseFragment;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.movie_list.item.MovieListItemModel;
import com.stockingd.cinedex.movie_list.item.MovieListViewHolder;
import com.stockingd.cinedex.movie_list.item.MovieListViewHolderFactory;
import com.stockingd.cinedex.utils.Units;
import com.stockingd.cinedex.widget.BindingListAdapter;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieListFragment extends BaseFragment implements MovieListContract.View {

    public static String EXTRA_ARGUMENTS = "EXTRA_ARGUMENTS";

    @BindView(R.id.movie_list) RecyclerView movieList;
    @BindView(R.id.refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.errror) View error;

    @Inject Units units;
    @Inject MovieListPresenter presenter;
    @Inject MovieListViewHolderFactory viewHolderFactory;

    @NonNull private Optional<BindingListAdapter<MovieListItemModel, MovieListViewHolder>> adapter
            = Optional.empty();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        MovieListFragmentArgs args = null;
        if (arguments != null) {
            args = arguments.getParcelable(EXTRA_ARGUMENTS);
        }

        MovieListFragmentArgs finalArgs = args;
        component().map(component -> {
            if (finalArgs != null) {
                return component.movieListComponent(new MovieListModule(this, finalArgs));
            }

            return null;
        }).ifPresent(c -> {
            c.inject(this);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (int) units.toDp(size.x);
        movieList.setLayoutManager(new GridLayoutManager(getActivity(), width / 180));
        adapter = Optional.of(new BindingListAdapter<MovieListItemModel, MovieListViewHolder>() {
            @Override
            public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.movie_list_item, parent, false);
                return viewHolderFactory.create(view);
            }
        });
        movieList.setAdapter(adapter.get());
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.requestModel();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestModel();
    }

    @OnClick(R.id.try_again)
    public void onTryAgainClicked() {
        presenter.requestModel();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
        error.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onModelUpdate(List<MovieListItemModel> model) {
        swipeRefreshLayout.setRefreshing(false);
        error.setVisibility(View.INVISIBLE);
        adapter.ifPresent(adapter -> {
            adapter.updateModel(model);
        });
    }

    @Override
    public void onError() {
        swipeRefreshLayout.setRefreshing(false);
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovieDetails() {

    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
