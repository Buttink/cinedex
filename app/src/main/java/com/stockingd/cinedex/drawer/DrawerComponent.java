package com.stockingd.cinedex.drawer;

import com.stockingd.cinedex.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {
        DrawerModule.class,
})
public interface DrawerComponent {

    void inject(DrawerFragment drawerFragment);
}
