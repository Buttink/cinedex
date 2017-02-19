package com.stockingd.cinedex.movie_details;

import com.stockingd.cinedex.ActivityRxJavaModule;
import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsFragment;
import com.stockingd.cinedex.movie_details.review.MovieReviewFragment;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        ActivityRxJavaModule.class,
        MovieDetailsActivityModule.class,
})
public interface MovieDetailsActivityComponent {

    void inject(MovieDetailsActivity activity);
}
