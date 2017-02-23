package com.stockingd.cinedex.movie_details.review.item;

import com.google.auto.value.AutoValue;

import java.net.URI;

@AutoValue
public abstract class LaunchReviewEvent {

    public static LaunchReviewEvent create(URI reviewUri) {
        return new AutoValue_LaunchReviewEvent(reviewUri);
    }

    public abstract URI reviewUri();
}
