package com.stockingd.cinedex.tmdb.json;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class MovieListResultObject {

    @Nullable
    @JsonField(name = "poster_path")
    public String posterPath;

    public boolean adult;

    public String overview;

    @JsonField(name = "release_date")
    public String releaseDate;

    @JsonField(name = "genre_ids")
    public int[] genreIds;

    public int id;

    @JsonField(name = "original_title")
    public String originalTitle;

    @JsonField(name = "original_language")
    public String originalLanguage;

    public String title;

    @Nullable
    @JsonField(name = "backdrop_path")
    public String backdropPath;

    public float popularity;

    @JsonField(name = "vote_count")
    public int voteCount;

    public boolean video;

    @JsonField(name = "vote_average")
    public float voteAverage;
}
