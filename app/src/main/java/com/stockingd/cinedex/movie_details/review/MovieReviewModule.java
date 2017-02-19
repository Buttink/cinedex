package com.stockingd.cinedex.movie_details.review;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieReviewModule {

    @NonNull private final MovieReviewContract.View view;
    @NonNull private final MovieReviewArgs args;

    public MovieReviewModule(@NonNull MovieReviewContract.View view, @NonNull MovieReviewArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @ViewScope
    MovieReviewContract.View providesMovieReviewContractView() {
        return view;
    }

    @Provides
    @ViewScope
    MovieReviewArgs providesMovieReviewArgs() {
        return args;
    }
}
