package com.stockingd.cinedex.movie_details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsPresenter;
import com.stockingd.cinedex.movie_details.review.MovieReviewPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

@ViewScope
public class MovieDetailsActivityPresenter implements MovieDetailsActivityContract.Presenter {

    private static final String TAG = "MovieDetailsActivityPre";
    @NonNull private final MovieDetailsActivityContract.View view;
    @NonNull private final MovieDetailsActivityArgs args;
    @NonNull private final MovieDetailsActivityService movieDetailsActivityService;

    private Subscription modelSubscription = Subscriptions.empty();

    @Inject
    public MovieDetailsActivityPresenter(
            @NonNull MovieDetailsActivityContract.View view,
            @NonNull MovieDetailsActivityArgs args,
            @NonNull MovieDetailsActivityService movieDetailsActivityService) {
        this.view = view;
        this.args = args;
        this.movieDetailsActivityService = movieDetailsActivityService;
    }

    @Override
    public void onResume() {
        modelSubscription.unsubscribe();
        modelSubscription = movieDetailsActivityService.getModel(args.movieId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::onModel,
                        throwable -> {
                            Log.e(TAG, "onResume: error while loading model", throwable);
                            view.onError();
                        });
    }

    @Override
    public void onRefresh() {
        onResume();
    }

    @Override
    public void onPause() {
        modelSubscription.unsubscribe();
    }
}
