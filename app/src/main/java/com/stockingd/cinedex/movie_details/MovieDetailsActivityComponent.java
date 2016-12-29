package com.stockingd.cinedex.movie_details;

import com.stockingd.cinedex.ActivityRxJavaModule;
import com.stockingd.cinedex.PerActivity;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsFragmentComponent;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {
        ActivityRxJavaModule.class,
        MovieDetailsActivityModule.class,
})
public interface MovieDetailsActivityComponent {

    void inject(MovieDetailsActivity activity);

    MovieDetailsFragmentComponent movieDetailsFragmentComponent(
            MovieDetailsModule movieDetailsModule);
}
