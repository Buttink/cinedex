package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.tmdb.TheMovieDbService;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

@ViewScope
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    @NonNull private final MovieDetailsContract.View view;
    @NonNull private final MovieDetailsFragmentArgs args;
    @NonNull private final MovieDetailsService movieDetailsService;

    @NonNull private Subscription modelSubscription = Subscriptions.empty();

    @Inject
    public MovieDetailsPresenter(@NonNull MovieDetailsContract.View view,
                                 @NonNull MovieDetailsFragmentArgs args,
                                 @NonNull MovieDetailsService movieDetailsService) {
        this.view = view;
        this.args = args;
        this.movieDetailsService = movieDetailsService;
    }

    @Override
    public void onResume() {
        modelSubscription = movieDetailsService.getModel(args.movieId())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onModel);
    }

    @Override
    public void onPause() {
        modelSubscription.unsubscribe();
    }
}
