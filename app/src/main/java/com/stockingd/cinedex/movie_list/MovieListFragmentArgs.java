package com.stockingd.cinedex.movie_list;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieListFragmentArgs implements Parcelable {

    public enum Mode { MostPopular, HighestRated, Favorites }

    public static MovieListFragmentArgs create(Mode mode) {
        return new AutoValue_MovieListFragmentArgs(mode);
    }

    public abstract Mode mode();
}
