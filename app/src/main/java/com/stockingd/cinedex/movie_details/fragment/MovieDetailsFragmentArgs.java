package com.stockingd.cinedex.movie_details.fragment;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieDetailsFragmentArgs implements Parcelable {

    public static MovieDetailsFragmentArgs create(long movieId) {
        return new AutoValue_MovieDetailsFragmentArgs(movieId);
    }

    public abstract long movieId();
}
