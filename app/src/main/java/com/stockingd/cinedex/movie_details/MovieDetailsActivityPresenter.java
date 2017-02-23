package com.stockingd.cinedex.movie_details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.dmstocking.optional.java.util.Optional;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.favorite.FavoritesRepository;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsModel;
import com.stockingd.cinedex.movie_details.review.item.LaunchReviewEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.OnCheckedChanged;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

@ViewScope
public class MovieDetailsActivityPresenter implements MovieDetailsActivityContract.Presenter {

    private static final String TAG = "MovieDetailsActivityPre";

    @NonNull private final MovieDetailsActivityContract.View view;
    @NonNull private final MovieDetailsActivityArgs args;
    @NonNull private final EventBus eventBus;
    @NonNull private final MovieDetailsActivityService movieDetailsActivityService;

    private Subscription modelSubscription = Subscriptions.empty();

    @Inject
    public MovieDetailsActivityPresenter(
            @NonNull MovieDetailsActivityContract.View view,
            @NonNull MovieDetailsActivityArgs args,
            @NonNull EventBus eventBus,
            @NonNull MovieDetailsActivityService movieDetailsActivityService) {
        this.view = view;
        this.args = args;
        this.eventBus = eventBus;
        this.movieDetailsActivityService = movieDetailsActivityService;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
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

    @Subscribe
    public void launchReview(LaunchReviewEvent event) {
        view.onLaunchReviewEvent(event.reviewUri());
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
        modelSubscription.unsubscribe();
    }
}
