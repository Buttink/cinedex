package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

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
    @ViewScope
    MovieListContract.View providesMovieListContractView() {
        return view;
    }

    @Provides
    @ViewScope
    MovieListFragmentArgs providesMovieListFragmentArgs() {
        return args;
    }
}
