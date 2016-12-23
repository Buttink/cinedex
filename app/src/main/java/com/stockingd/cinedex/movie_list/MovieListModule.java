package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    @NonNull private final MovieListContract.View view;

    public MovieListModule(@NonNull MovieListContract.View view) {
        this.view = view;
    }

    @Provides
    @PerFragment
    MovieListContract.View providesMovieListContractView() {
        return view;
    }
}
