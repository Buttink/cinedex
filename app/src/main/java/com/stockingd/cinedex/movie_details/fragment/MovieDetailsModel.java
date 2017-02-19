package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.github.dmstocking.optional.java.util.Optional;

import java.util.Date;

@AutoValue
public abstract class MovieDetailsModel {

    public static MovieDetailsModel create(@Nullable String backdropPath,
                                           @Nullable String posterPath,
                                           String title,
                                           int year,
                                           @Nullable Date release,
                                           int runtime,
                                           float rating,
                                           String overview) {
        return new AutoValue_MovieDetailsModel(Optional.ofNullable(backdropPath),
                                               Optional.ofNullable(posterPath),
                                               title,
                                               year,
                                               Optional.ofNullable(release),
                                               runtime,
                                               rating,
                                               overview);
    }

    public abstract Optional<String> backdropPath();
    public abstract Optional<String> posterPath();
    public abstract String title();
    public abstract int year();
    public abstract Optional<Date> release();
    public abstract int runtime();
    public abstract float rating();
    public abstract String overview();
}
