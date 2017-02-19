package com.stockingd.cinedex.movie_details.review;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieReviewArgs implements Parcelable {

    public static MovieReviewArgs create(long movieId) {
        return new AutoValue_MovieReviewArgs(movieId);
    }

    public abstract long movieId();
}
