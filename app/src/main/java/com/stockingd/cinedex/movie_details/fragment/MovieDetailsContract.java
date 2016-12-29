package com.stockingd.cinedex.movie_details.fragment;

public interface MovieDetailsContract {

    interface View {
        void onModel(MovieDetailsModel model);
        void onError();
    }

    interface Presenter {
        void onResume();
    }
}
