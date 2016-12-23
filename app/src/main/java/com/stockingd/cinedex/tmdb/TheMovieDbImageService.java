package com.stockingd.cinedex.tmdb;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.stockingd.cinedex.tmdb.model.PosterSize;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class TheMovieDbImageService {

    @NonNull private final TheMovieDbConfigService theMovieDbConfigService;

    @Inject
    public TheMovieDbImageService(@NonNull TheMovieDbConfigService theMovieDbConfigService) {
        this.theMovieDbConfigService = theMovieDbConfigService;
    }

    public Observable<Uri> imageUri(ImageView view, String path) {
        return theMovieDbConfigService.getConfiguration()
                .map(config -> {
                    int width = view.getWidth();
                    String size = Observable.from(config.getPosterSizes())
                            .filter(posterSize -> posterSize.width() > width)
                            .map(PosterSize::size)
                            .defaultIfEmpty("original")
                            .toBlocking()
                            .first();
                    return Uri.parse(config.getBaseUrl() + size + path);
                });
    }
}
