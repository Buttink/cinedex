package com.stockingd.cinedex.tmdb;

import com.stockingd.cinedex.tmdb.json.GetConfiguration;
import com.stockingd.cinedex.tmdb.json.GetPopularMovieResults;
import com.stockingd.cinedex.tmdb.json.GetTopRatedMovieResults;

import retrofit2.http.GET;
import rx.Observable;

public interface TheMovieDbService {

    @GET("/3/configuration")
    Observable<GetConfiguration> getConfiguration();

    @GET("/3/movie/popular")
    Observable<GetPopularMovieResults> getPopularMovies();

    @GET("/3/movie/top_rated")
    Observable<GetTopRatedMovieResults> getTopRatedMovies();

//    @GET("/movie/{movie_id}")
//    List<MovieDetails> getMovieDetails(@Path("movie_id") int movieId);
}
