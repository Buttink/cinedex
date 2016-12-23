package com.stockingd.cinedex.tmdb.model;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.tmdb.json.GetConfiguration;
import com.stockingd.cinedex.utils.Lazy;

import java.util.List;

import rx.Observable;

public class TheMovieDbConfig {

    @NonNull private final GetConfiguration getConfiguration;

    private Lazy<List<PosterSize>> posterSizes;

    public TheMovieDbConfig(@NonNull GetConfiguration getConfiguration) {
        this.getConfiguration = getConfiguration;
        posterSizes = new Lazy<>(() -> {
            return Observable.from(getConfiguration.images.posterSizes)
                    .map(s -> {
                        int width = Integer.MAX_VALUE;
                        try {
                            width = Integer.parseInt(s.substring(1));
                        } catch (NumberFormatException ignored) { }
                        return PosterSize.create(s, width);
                    })
                    .sorted((o1, o2) -> -Integer.compare(o1.width(), o2.width()))
                    .toList()
                    .toBlocking()
                    .first();
        });
    }

    public String getBaseUrl() {
        return getConfiguration.images.secureBaseUrl;
    }

    public List<PosterSize> getPosterSizes() {
        return posterSizes.get();
    }
}
