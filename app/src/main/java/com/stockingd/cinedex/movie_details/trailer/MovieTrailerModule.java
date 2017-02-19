package com.stockingd.cinedex.movie_details.trailer;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieTrailerModule {

    @NonNull private final MovieTrailerContract.View view;
    @NonNull private final MovieTrailerArgs args;

    public MovieTrailerModule(@NonNull MovieTrailerContract.View view,
                              @NonNull MovieTrailerArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @ViewScope
    @NonNull
    MovieTrailerContract.View getView() {
        return view;
    }

    @Provides
    @ViewScope
    @NonNull
    MovieTrailerArgs getArgs() {
        return args;
    }
}
