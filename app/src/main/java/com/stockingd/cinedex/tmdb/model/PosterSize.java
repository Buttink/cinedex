package com.stockingd.cinedex.tmdb.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PosterSize {

    public static PosterSize create(String size, int width) {
        return new AutoValue_PosterSize(size, width);
    }

    public abstract String size();
    public abstract int width();
}
