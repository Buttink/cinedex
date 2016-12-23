package com.stockingd.cinedex.tmdb;

import com.stockingd.cinedex.tmdb.json.GetConfiguration;
import com.stockingd.cinedex.tmdb.json.GetPopularMovieResultsWrapper;

import retrofit2.http.GET;
import rx.Observable;

public interface TheMovieDbService {

    @GET("/3/movie/popular")
    Observable<GetPopularMovieResultsWrapper> getPopularMovies();

    @GET("/3/configuration")
    Observable<GetConfiguration> getConfiguration();

//    @GET("/movie/{movie_id}")
//    List<MovieDetails> getMovieDetails(@Path("movie_id") int movieId);
}
