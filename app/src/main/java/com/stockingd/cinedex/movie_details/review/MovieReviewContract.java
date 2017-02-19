package com.stockingd.cinedex.movie_details.review;

import java.util.List;

public interface MovieReviewContract {

    interface Presenter {
        void onResume();
        void onPause();
    }

    interface View {
        void onModel(List<MovieReviewModel> model);
    }
}
