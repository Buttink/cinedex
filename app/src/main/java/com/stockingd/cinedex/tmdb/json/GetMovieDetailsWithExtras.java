package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class GetMovieDetailsWithExtras extends GetMovieDetails {

    public GetMovieVideos videos;
    public GetMovieReviews reviews;
}
