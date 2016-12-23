package com.stockingd.cinedex.drawer;

import com.stockingd.cinedex.PerFragment;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class DrawerModule {

    @Provides
    @PerFragment
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
