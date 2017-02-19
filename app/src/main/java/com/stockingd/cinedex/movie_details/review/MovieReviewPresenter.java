package com.stockingd.cinedex.movie_details.review;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import javax.inject.Inject;
import javax.xml.validation.Schema;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

@ViewScope
public class MovieReviewPresenter implements MovieReviewContract.Presenter {

    @NonNull private final MovieReviewContract.View view;
    @NonNull private final MovieReviewService movieReviewService;

    @NonNull private Subscription modelSubscription = Subscriptions.empty();

    @Inject
    public MovieReviewPresenter(@NonNull MovieReviewContract.View view,
                                @NonNull MovieReviewService movieReviewService) {
        this.view = view;
        this.movieReviewService = movieReviewService;
    }

    @Override
    public void onResume() {
        modelSubscription.unsubscribe();
        modelSubscription = movieReviewService.getModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onModel);
    }

    @Override
    public void onPause() {
        modelSubscription.unsubscribe();
    }
}
