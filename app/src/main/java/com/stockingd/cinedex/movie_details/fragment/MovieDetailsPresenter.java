package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stockingd.cinedex.PerFragment;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

@PerFragment
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private static final String TAG = "MovieDetailsPresenter";

    @NonNull private final MovieDetailsContract.View view;
    @NonNull private final CompositeSubscription compositeSubscription;
    @NonNull private final MovieDetailsFragmentArgs args;
    @NonNull private final MovieDetailsService movieDetailsService;

    @NonNull private Subscription modelSubscription;

    @Inject
    public MovieDetailsPresenter(@NonNull MovieDetailsContract.View view,
                                 @NonNull @Named("fragment") CompositeSubscription compositeSubscription,
                                 @NonNull MovieDetailsFragmentArgs args,
                                 @NonNull MovieDetailsService movieDetailsService) {
        this.view = view;
        this.compositeSubscription = compositeSubscription;
        this.args = args;
        this.movieDetailsService = movieDetailsService;
    }

    @Override
    public void onResume() {
        compositeSubscription.remove(modelSubscription);
        modelSubscription = movieDetailsService.getModel(args.movieId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    view.onModel(model);
                }, throwable -> {
                    Log.e(TAG, "onResume: error while loading model", throwable);
                    view.onError();
                });
        compositeSubscription.add(modelSubscription);
    }
}
