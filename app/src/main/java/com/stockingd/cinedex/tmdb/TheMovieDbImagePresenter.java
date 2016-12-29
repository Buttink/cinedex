package com.stockingd.cinedex.tmdb;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stockingd.cinedex.R;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Completable;
import rx.android.schedulers.AndroidSchedulers;

@Singleton
public class TheMovieDbImagePresenter {

    @NonNull private final Picasso picasso;
    @NonNull private final TheMovieDbImageService theMovieDbImageService;

    @Inject
    public TheMovieDbImagePresenter(@NonNull Picasso picasso,
                                    @NonNull TheMovieDbImageService theMovieDbImageService) {
        this.picasso = picasso;
        this.theMovieDbImageService = theMovieDbImageService;
    }

    public Completable loadBackdrop(String path, ImageView imageView) {
        return load(path, imageView, R.drawable.ic_placeholder_16_9);
    }

    public Completable loadPoster(String path, ImageView imageView) {
        return load(path, imageView, R.drawable.ic_placeholde_2_3);
    }

    private Completable load(String path, ImageView imageView, @DrawableRes int placeholder) {
        return theMovieDbImageService.imageUri(imageView, path)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(uri -> {
                    picasso.load(uri)
                            .placeholder(placeholder)
                            .fit()
                            .into(imageView);
                })
                .toCompletable();
    }
}
