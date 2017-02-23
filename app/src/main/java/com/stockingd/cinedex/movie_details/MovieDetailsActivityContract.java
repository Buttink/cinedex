package com.stockingd.cinedex.movie_details;

import java.net.URI;

public interface MovieDetailsActivityContract {

    interface Presenter {
        void onResume();
        void onRefresh();
        void onPause();
    }

    interface View {
        void onModel(MovieDetailsActivityModel model);
        void onError();

        void onLaunchReviewEvent(URI reviewUri);
    }
}
