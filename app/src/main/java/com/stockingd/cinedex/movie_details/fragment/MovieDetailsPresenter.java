package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.github.dmstocking.optional.java.util.Optional;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.favorite.FavoriteEntity;
import com.stockingd.cinedex.favorite.FavoritesRepository;
import com.stockingd.cinedex.favorite.data.Favorite;

import javax.inject.Inject;

import butterknife.OnCheckedChanged;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

@ViewScope
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    @NonNull private final MovieDetailsContract.View view;
    @NonNull private final MovieDetailsFragmentArgs args;
    @NonNull private final FavoritesRepository favoritesRepository;
    @NonNull private final MovieDetailsService movieDetailsService;

    @NonNull private Subscription modelSubscription = Subscriptions.empty();

    private Optional<MovieDetailsModel> model = Optional.empty();

    @Inject
    public MovieDetailsPresenter(@NonNull MovieDetailsContract.View view,
                                 @NonNull MovieDetailsFragmentArgs args,
                                 @NonNull FavoritesRepository favoritesRepository,
                                 @NonNull MovieDetailsService movieDetailsService) {
        this.view = view;
        this.args = args;
        this.favoritesRepository = favoritesRepository;
        this.movieDetailsService = movieDetailsService;
    }

    @Override
    public void onResume() {
        modelSubscription = movieDetailsService.getModel(args.movieId())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                            this.model = Optional.of(model);
                            view.onModel(model);
                        },
                        ignored -> {

                        });
    }

    @OnCheckedChanged(R.id.favorite)
    public void onFavoriteClicked(boolean checked) {
        model.ifPresent(model -> {
            if (checked) {
                favoritesRepository.insert(
                        FavoriteEntity.create(
                                model.id(),
                                model.title(),
                                model.rating(),
                                null))
                        .subscribe();
            } else {
                favoritesRepository.delete(model.id())
                        .subscribe();
            }
        });
    }

    @Override
    public void onPause() {
        modelSubscription.unsubscribe();
    }
}
