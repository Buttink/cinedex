package com.stockingd.cinedex;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class FragmentRxJavaModule {

    @Provides
    @ViewScope
    @Named("fragment")
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
