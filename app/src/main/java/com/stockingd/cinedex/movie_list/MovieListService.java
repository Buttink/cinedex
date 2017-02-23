package com.stockingd.cinedex.movie_list;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.favorite.FavoritesRepository;
import com.stockingd.cinedex.movie_list.item.MovieListItemModel;
import com.stockingd.cinedex.tmdb.TheMovieDbService;
import com.github.dmstocking.optional.java.util.Optional;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MovieListService {

    @NonNull private final FavoritesRepository favoritesRepository;
    @NonNull private final TheMovieDbService theMovieDbService;

    @Inject
    public MovieListService(@NonNull FavoritesRepository favoritesRepository,
                            @NonNull TheMovieDbService theMovieDbService) {
        this.favoritesRepository = favoritesRepository;
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<List<MovieListItemModel>> getMostPopularMoviesModel() {
        return theMovieDbService.getPopularMovies().flatMap(wrapper -> {
            return Observable.from(wrapper.results);
        }).map(item -> {
            return MovieListItemModel.create(
                    item.id,
                    Optional.ofNullable(item.posterPath),
                    item.title,
                    String.valueOf(Math.round(item.voteAverage)));
        }).toList();
    }

    public Observable<List<MovieListItemModel>> getHighestRatedMoviesModel() {
        return theMovieDbService.getTopRatedMovies().flatMap(wrapper -> {
            return Observable.from(wrapper.results);
        }).map(item -> {
            return MovieListItemModel.create(
                    item.id,
                    Optional.ofNullable(item.posterPath),
                    item.title,
                    String.valueOf(Math.round(item.voteAverage)));
        }).toList();
    }

    public Observable<List<MovieListItemModel>> getFavoritesMoviesModel() {
        return favoritesRepository.getFavoritesAsync()
                .flatMap(Observable::from)
                .map(entity -> {
                    return MovieListItemModel.create(
                            entity.id(),
                            Optional.empty(),
                            entity.title(),
                            String.valueOf(Math.round(entity.rating()*10)));
                })
                .toList();
    }
}
