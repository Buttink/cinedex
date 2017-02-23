package com.stockingd.cinedex;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.stockingd.cinedex.tmdb.TheMovieDbModule;

public class CinedexApplication extends Application {

    private CinedexComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
        component = DaggerCinedexComponent.builder()
                .androidModule(new AndroidModule(this))
                .theMovieDbModule(new TheMovieDbModule())
                .build();
    }

    public CinedexComponent component() {
        return component;
    }
}
