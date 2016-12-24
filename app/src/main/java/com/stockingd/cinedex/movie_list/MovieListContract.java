package com.stockingd.cinedex.movie_list;

import com.stockingd.cinedex.movie_list.item.MovieListItemModel;

import java.util.List;

public interface MovieListContract {

    interface View {
        void onModelUpdate(List<MovieListItemModel> model);
        void showMovieDetails();
        void showProgress();
    }

    interface Presenter {
        void onResume();
        void onPause();
        void onItemClicked(long movieId);
    }
}
