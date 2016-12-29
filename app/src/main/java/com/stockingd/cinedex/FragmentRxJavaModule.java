package com.stockingd.cinedex;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class FragmentRxJavaModule {

    @Provides
    @PerFragment
    @Named("fragment")
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
