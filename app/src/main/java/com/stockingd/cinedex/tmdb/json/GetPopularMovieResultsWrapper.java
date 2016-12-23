package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class GetPopularMovieResultsWrapper {

    public int page;

    public List<GetPopularMovieResults> results;

    @JsonField(name = "total_results")
    public int totalResults;

    @JsonField(name = "total_pages")
    public int totalPages;
}
