package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.movie_list.item.MovieListItemModel;
import com.stockingd.cinedex.tmdb.TheMovieDbService;
import com.stockingd.cinedex.utils.URIUtils;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MovieListService {

    @NonNull private final TheMovieDbService theMovieDbService;

    @Inject
    public MovieListService(@NonNull TheMovieDbService theMovieDbService) {
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<List<MovieListItemModel>> getModel() {
        return theMovieDbService.getPopularMovies().flatMap(wrapper -> {
            return Observable.from(wrapper.results);
        }).map(item -> {
            return MovieListItemModel.create(
                    item.id,
                    Optional.ofNullable(item.posterPath),
                    item.title,
                    item.overview,
                    String.valueOf(Math.round(item.voteAverage*10)/10));
        }).toList();
    }
}
