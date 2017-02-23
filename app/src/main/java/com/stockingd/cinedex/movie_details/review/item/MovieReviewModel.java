package com.stockingd.cinedex.movie_details.review.item;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.stockingd.cinedex.widget.Identifiable;

@AutoValue
public abstract class MovieReviewModel implements Identifiable<MovieReviewModel>, Parcelable {

    public static MovieReviewModel create(String id, String author, String review, String url) {
        return new AutoValue_MovieReviewModel(id, author, review, url);
    }

    public abstract String id();
    public abstract String author();
    public abstract String review();
    public abstract String url();

    @Override
    public boolean identify(MovieReviewModel other) {
        return id().equals(other.id());
    }
}
