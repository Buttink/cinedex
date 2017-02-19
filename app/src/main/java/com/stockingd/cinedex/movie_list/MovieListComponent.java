package com.stockingd.cinedex.movie_list;

import com.stockingd.cinedex.FragmentRxJavaModule;
import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        MovieListModule.class,
        FragmentRxJavaModule.class,
})
public interface MovieListComponent {
    void inject(MovieListFragment fragment);
}
