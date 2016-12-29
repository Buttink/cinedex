package com.stockingd.cinedex.tmdb.json;

import android.support.annotation.Nullable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class GetMovieDetails {

    public boolean adult;

    @Nullable
    @JsonField(name = "backdrop_path")
    public String backdropPath;

    public int budget;

    public List<GenresObject> genres;

    public String homepage;

    public int id;

    @JsonField(name = "imdb_id")
    public String imdbId;

    @JsonField(name = "original_title")
    public String originalTitle;

    @JsonField(name = "original_language")
    public String originalLanguage;

    public String overview;

    public float popularity;

    @Nullable
    @JsonField(name = "poster_path")
    public String posterPath;

    @JsonField(name = "release_date")
    public String releaseDate;

    public int revenue;

    public int runtime;

    public String status;

    public String tagline;

    public String title;

    public boolean video;

    @JsonField(name = "vote_average")
    public float voteAverage;

    @JsonField(name = "vote_count")
    public int voteCount;
}
