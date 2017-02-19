package com.stockingd.cinedex.movie_details;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieDetailsActivityArgs implements Parcelable {

    public static MovieDetailsActivityArgs create(long movieId) {
        return new AutoValue_MovieDetailsActivityArgs(movieId);
    }

    public abstract long movieId();
}
