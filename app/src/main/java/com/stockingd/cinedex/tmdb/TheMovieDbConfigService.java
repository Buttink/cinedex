package com.stockingd.cinedex.tmdb;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.tmdb.model.TheMovieDbConfig;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

@Singleton
public class TheMovieDbConfigService {

    @NonNull private final TheMovieDbService service;

    private ConnectableObservable<TheMovieDbConfig> observable;
    private Subscription configSubscription;

    @Inject
    public TheMovieDbConfigService(@NonNull TheMovieDbService service) {
        this.service = service;
    }

    public Observable<TheMovieDbConfig> getConfiguration() {
        if (configSubscription == null) {
            observable = service.getConfiguration()
                    .map(TheMovieDbConfig::new)
                    .replay(1);
            configSubscription = observable.connect();
        }

        return observable;
    }
}
