package com.stockingd.cinedex.drawer;

import com.stockingd.cinedex.FragmentRxJavaModule;
import com.stockingd.cinedex.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {
        DrawerModule.class,
        FragmentRxJavaModule.class,
})
public interface DrawerComponent {

    void inject(DrawerFragment drawerFragment);
}
