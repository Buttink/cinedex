package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;
import com.stockingd.cinedex.movie_list.item.MovieListItemModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

@PerFragment
public class MovieListPresenter implements MovieListContract.Presenter {

    @NonNull private final MovieListContract.View view;
    @NonNull private final MovieListFragmentArgs movieListFragmentArgs;
    @NonNull private final MovieListService movieListService;

    @NonNull private Subscription modelSubscription = Subscriptions.empty();

    @Inject
    public MovieListPresenter(@NonNull MovieListContract.View view,
                              @NonNull MovieListFragmentArgs movieListFragmentArgs,
                              @NonNull MovieListService movieListService) {
        this.view = view;
        this.movieListFragmentArgs = movieListFragmentArgs;
        this.movieListService = movieListService;
    }

    @Override
    public void onResume() {
        view.showProgress();
        modelSubscription.unsubscribe();
        Observable<List<MovieListItemModel>> observable;
        switch (movieListFragmentArgs.mode()) {
            default:
            case MostPopular:
                observable = movieListService.getMostPopularMoviesModel();
                break;
            case HighestRated:
                observable = movieListService.getHighestRatedMoviesModel();
                break;
        }
        modelSubscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    view.onModelUpdate(model);
                }, error -> {
                    view.onError();
                });
    }

    @Override
    public void onPause() {
        modelSubscription.unsubscribe();
    }

    @Override
    public void onItemClicked(long movieId) {
//        view.showMovieDetails(movieId);
    }
}
