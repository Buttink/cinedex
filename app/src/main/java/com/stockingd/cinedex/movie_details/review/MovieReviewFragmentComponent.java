package com.stockingd.cinedex.movie_details.review;

import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        MovieReviewModule.class,
})
public interface MovieReviewFragmentComponent {

    void inject(MovieReviewFragment movieReviewFragment);
}
