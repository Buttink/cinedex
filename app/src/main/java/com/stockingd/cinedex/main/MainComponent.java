package com.stockingd.cinedex.main;

import com.stockingd.cinedex.ActivityRxJavaModule;
import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        MainModule.class,
        ActivityRxJavaModule.class,
})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
