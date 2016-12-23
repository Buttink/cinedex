package com.stockingd.cinedex;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class FragmentRxJavaModule {

    @Provides
    @PerFragment
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
