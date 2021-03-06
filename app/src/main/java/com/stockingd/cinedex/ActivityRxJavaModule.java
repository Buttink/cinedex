package com.stockingd.cinedex;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class ActivityRxJavaModule {

    @Provides
    @ViewScope
    @Named("activity")
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
