package com.stockingd.cinedex.movie_list;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
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

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListFragment extends BaseFragment implements MovieListContract.View {

    @BindView(R.id.movie_list) RecyclerView movieList;
    @BindView(R.id.progress) ProgressBar progressBar;

    @Inject Units units;
    @Inject MovieListPresenter presenter;
    @Inject MovieListViewHolderFactory viewHolderFactory;

    private Optional<BindingListAdapter<MovieListItemModel, MovieListViewHolder>> adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        component().map(component -> {
            return component.movieListComponent(new MovieListModule(this));
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
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onModelUpdate(List<MovieListItemModel> model) {
        progressBar.setVisibility(View.INVISIBLE);
        adapter.ifPresent(adapter -> {
            adapter.updateModel(model);
        });
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
