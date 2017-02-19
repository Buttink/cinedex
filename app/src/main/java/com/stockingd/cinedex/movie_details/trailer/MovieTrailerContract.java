package com.stockingd.cinedex.movie_details.trailer;

public interface MovieTrailerContract {

    interface Presenter {
        void onResume();
        void onTrailerClicked();
    }

    interface View {
        void onModel(String key);

        void launchTrailer();
    }
}
