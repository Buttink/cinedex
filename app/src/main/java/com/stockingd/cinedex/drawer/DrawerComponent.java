package com.stockingd.cinedex.drawer;

import com.stockingd.cinedex.FragmentRxJavaModule;
import com.stockingd.cinedex.ViewScope;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = {
        DrawerModule.class,
        FragmentRxJavaModule.class,
})
public interface DrawerComponent {

    void inject(DrawerFragment drawerFragment);
}
