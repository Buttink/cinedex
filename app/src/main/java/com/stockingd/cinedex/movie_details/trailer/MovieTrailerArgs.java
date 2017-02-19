package com.stockingd.cinedex.movie_details.trailer;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieTrailerArgs implements Parcelable {

    public static MovieTrailerArgs create(@NonNull String key) {
        return new AutoValue_MovieTrailerArgs(key);
    }

    public abstract String key();
}
