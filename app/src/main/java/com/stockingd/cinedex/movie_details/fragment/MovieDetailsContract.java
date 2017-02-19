package com.stockingd.cinedex.movie_details.fragment;

public interface MovieDetailsContract {

    interface View {
        void onModel(MovieDetailsModel model);
    }

    interface Presenter {
        void onResume();
        void onPause();
    }
}
