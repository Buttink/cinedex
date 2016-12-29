package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsModule {

    @NonNull private final MovieDetailsContract.View view;
    @NonNull private final MovieDetailsFragmentArgs args;

    public MovieDetailsModule(@NonNull MovieDetailsContract.View view,
                              @NonNull MovieDetailsFragmentArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @PerFragment
    MovieDetailsContract.View providesMovieDetailsContractView() {
        return view;
    }

    @Provides
    @PerFragment
    MovieDetailsFragmentArgs providesMovieDetailsFragmentArgs() {
        return args;
    }
}
