package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    @NonNull private final MovieListContract.View view;
    @NonNull private final MovieListFragmentArgs args;

    public MovieListModule(@NonNull MovieListContract.View view,
                           @NonNull MovieListFragmentArgs args) {
        this.view = view;
        this.args = args;
    }

    @Provides
    @PerFragment
    MovieListContract.View providesMovieListContractView() {
        return view;
    }

    @Provides
    @PerFragment
    MovieListFragmentArgs providesMovieListFragmentArgs() {
        return args;
    }
}
