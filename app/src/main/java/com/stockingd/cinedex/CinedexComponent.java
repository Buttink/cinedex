package com.stockingd.cinedex;

import com.stockingd.cinedex.movie_list.MovieListComponent;
import com.stockingd.cinedex.movie_list.MovieListModule;
import com.stockingd.cinedex.tmdb.TheMovieDbModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        CinedexModule.class,
        TheMovieDbModule.class,
})
public interface CinedexComponent {

    MovieListComponent movieListComponent(MovieListModule movieListModule);
}
