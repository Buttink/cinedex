package com.stockingd.cinedex;

import android.app.Application;

import com.stockingd.cinedex.tmdb.TheMovieDbModule;

public class CinedexApplication extends Application {

    private CinedexComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerCinedexComponent.builder()
                .androidModule(new AndroidModule(this))
                .theMovieDbModule(new TheMovieDbModule())
                .build();
    }

    public CinedexComponent component() {
        return component;
    }
}
