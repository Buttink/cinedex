package com.stockingd.cinedex.movie_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockingd.cinedex.BaseFragment;
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

public class MovieListFragment extends BaseFragment implements MovieListContract.View {

    @BindView(R.id.movie_list) RecyclerView movieList;

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
        Context context = new ContextThemeWrapper(getActivity(), R.style.AppTheme_Dark);
        inflater = inflater.cloneInContext(context);
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        int width = (int) units.toDp(movieList.getWidth());
        int spanCount = width / 120;
        movieList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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
    public void onModelUpdate(List<MovieListItemModel> model) {
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
