package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsFragmentModule {

    @NonNull private final MovieDetailsContract.View view;
    @NonNull private final MovieDetailsFragmentArgs args;

    public MovieDetailsFragmentModule(@NonNull MovieDetailsContract.View view,
                                      @NonNull MovieDetailsFragmentArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @ViewScope
    MovieDetailsContract.View providesMovieDetailsContractView() {
        return view;
    }

    @Provides
    @ViewScope
    MovieDetailsFragmentArgs providesMovieDetailsFragmentArgs() {
        return args;
    }
}
