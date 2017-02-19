package com.stockingd.cinedex.tmdb;

import com.stockingd.cinedex.tmdb.json.GetConfiguration;
import com.stockingd.cinedex.tmdb.json.GetMovieDetails;
import com.stockingd.cinedex.tmdb.json.GetMovieDetailsWithExtras;
import com.stockingd.cinedex.tmdb.json.GetMovieReviews;
import com.stockingd.cinedex.tmdb.json.GetPopularMovieResults;
import com.stockingd.cinedex.tmdb.json.GetTopRatedMovieResults;
import com.stockingd.cinedex.tmdb.json.GetMovieVideos;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface TheMovieDbService {

    @GET("/3/configuration")
    Observable<GetConfiguration> getConfiguration();

    @GET("/3/movie/popular")
    Observable<GetPopularMovieResults> getPopularMovies();

    @GET("/3/movie/top_rated")
    Observable<GetTopRatedMovieResults> getTopRatedMovies();

    @GET("/3/movie/{movie_id}")
    Observable<GetMovieDetails> getMovieDetails(@Path("movie_id") long movieId);

    @GET("/3/movie/{movie_id}/videos")
    Observable<GetMovieVideos> getMovieVideos(@Path("movie_id") long movieId);

    @GET("/3/movie/{movie_id}/reviews")
    Observable<GetMovieReviews> getMovieReviews(@Path("movie_id") long movieId);

    @GET("/3/movie/{movie_id}?append_to_response=videos,reviews")
    Observable<GetMovieDetailsWithExtras> getMovieDetailsWithExtras(@Path("movie_id") long movieId);
}
