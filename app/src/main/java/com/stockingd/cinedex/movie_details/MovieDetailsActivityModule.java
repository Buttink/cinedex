package com.stockingd.cinedex.movie_details;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsActivityModule {

    @NonNull private final BackdropView backdropView;

    public MovieDetailsActivityModule(@NonNull BackdropView backdropView) {
        this.backdropView = backdropView;
    }

    @Provides
    @PerActivity
    BackdropView providesBackdropView() {
        return backdropView;
    }
}
