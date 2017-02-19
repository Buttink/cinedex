package com.stockingd.cinedex.movie_details.trailer;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import java.net.URI;

import javax.inject.Inject;

@ViewScope
public class MovieTrailerPresenter implements MovieTrailerContract.Presenter {

    @NonNull private final MovieTrailerContract.View view;
    @NonNull private final MovieTrailerArgs args;

    @Inject
    public MovieTrailerPresenter(@NonNull MovieTrailerContract.View view,
                                 @NonNull MovieTrailerArgs args) {
        this.view = view;
        this.args = args;
    }

    @Override
    public void onResume() {
        view.onModel(args.key());
    }

    @Override
    public void onTrailerClicked() {
        view.launchTrailer();
    }
}
