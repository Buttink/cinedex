package com.stockingd.cinedex.movie_details.fragment;

import com.stockingd.cinedex.FragmentRxJavaModule;
import com.stockingd.cinedex.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {
        FragmentRxJavaModule.class,
        MovieDetailsModule.class,
})
public interface MovieDetailsFragmentComponent {

    void inject(MovieDetailsFragment movieDetailsFragment);
}
