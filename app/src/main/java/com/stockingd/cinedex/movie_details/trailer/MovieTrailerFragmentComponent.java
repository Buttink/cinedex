package com.stockingd.cinedex.movie_details.trailer;

import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        MovieTrailerModule.class,
})
public interface MovieTrailerFragmentComponent {

    void inject(MovieTrailerFragment fragment);
}
