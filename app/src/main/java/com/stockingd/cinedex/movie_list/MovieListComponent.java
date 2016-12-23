package com.stockingd.cinedex.movie_list;

import com.stockingd.cinedex.FragmentRxJavaModule;
import com.stockingd.cinedex.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {
        MovieListModule.class,
        FragmentRxJavaModule.class,
})
public interface MovieListComponent {
    void inject(MovieListFragment fragment);
}
