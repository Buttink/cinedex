package com.stockingd.cinedex;

import com.stockingd.cinedex.drawer.DrawerComponent;
import com.stockingd.cinedex.drawer.DrawerModule;
import com.stockingd.cinedex.main.MainComponent;
import com.stockingd.cinedex.main.MainModule;
import com.stockingd.cinedex.movie_details.MovieDetailsActivityComponent;
import com.stockingd.cinedex.movie_details.MovieDetailsActivityModule;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsFragmentComponent;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsFragmentModule;
import com.stockingd.cinedex.movie_details.review.MovieReviewFragmentComponent;
import com.stockingd.cinedex.movie_details.review.MovieReviewModule;
import com.stockingd.cinedex.movie_details.trailer.MovieTrailerFragmentComponent;
import com.stockingd.cinedex.movie_details.trailer.MovieTrailerModule;
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
    DrawerComponent drawerComponent(DrawerModule drawerModule);
    MainComponent mainComponent(MainModule mainModule, ActivityRxJavaModule activityRxJavaModule);
    MovieDetailsActivityComponent movieDetailsActivityComponent(MovieDetailsActivityModule movieDetailsActivityModule);
    MovieDetailsFragmentComponent movieDetailsFragmentComponent(MovieDetailsFragmentModule movieDetailsFragmentModule);
    MovieReviewFragmentComponent movieReviewFragmentComponent(MovieReviewModule movieReviewModule);
    MovieTrailerFragmentComponent movieTrailerFragmentComponent(MovieTrailerModule movieTrailerModule);
}
