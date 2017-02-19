package com.stockingd.cinedex.movie_details.fragment;

import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        MovieDetailsFragmentModule.class,
})
public interface MovieDetailsFragmentComponent {

    void inject(MovieDetailsFragment movieDetailsFragment);
}
