package com.stockingd.cinedex.main;

import com.stockingd.cinedex.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {
        MainModule.class,
})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
