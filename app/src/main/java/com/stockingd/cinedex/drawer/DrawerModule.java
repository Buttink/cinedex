package com.stockingd.cinedex.drawer;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class DrawerModule {

    @NonNull private final DrawerContract.View view;

    public DrawerModule(@NonNull DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    @ViewScope
    DrawerContract.View providesDrawerContractView() {
        return view;
    }


    @Provides
    @ViewScope
    CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
