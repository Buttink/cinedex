package com.stockingd.cinedex.movie_details;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsActivityModule {


    @NonNull private final MovieDetailsActivityContract.View view;
    @NonNull private final MovieDetailsActivityArgs args;

    public MovieDetailsActivityModule(@NonNull MovieDetailsActivityContract.View view,
                                      @NonNull MovieDetailsActivityArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @ViewScope
    MovieDetailsActivityArgs providesMovieDetailsActivityArgs() {
        return args;
    }

    @Provides
    @ViewScope
    MovieDetailsActivityContract.View getView() {
        return view;
    }
}
