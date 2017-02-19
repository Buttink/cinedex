package com.stockingd.cinedex.movie_details.review;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.tmdb.TheMovieDbService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@ViewScope
public class MovieReviewService {

    @NonNull private final MovieReviewArgs movieReviewArgs;
    @NonNull private final TheMovieDbService theMovieDbService;

    @Inject
    public MovieReviewService(@NonNull MovieReviewArgs movieReviewArgs,
                              @NonNull TheMovieDbService theMovieDbService) {
        this.movieReviewArgs = movieReviewArgs;
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<List<MovieReviewModel>> getModel() {
        return theMovieDbService.getMovieReviews(movieReviewArgs.movieId())
                .flatMapIterable(data -> data.results)
                .limit(3)
                .map(reviewResult ->
                        MovieReviewModel.create(
                                reviewResult.id,
                                reviewResult.author,
                                reviewResult.content,
                                reviewResult.url))
                .toList();
    }
}
