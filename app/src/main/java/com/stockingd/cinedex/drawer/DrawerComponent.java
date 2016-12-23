package com.stockingd.cinedex.drawer;

import com.stockingd.cinedex.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = {
        DrawerModule.class,
})
public interface DrawerComponent {
}
