package com.stockingd.cinedex.main;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @NonNull private final MainContract.View view;

    public MainModule(@NonNull MainContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    MainContract.View providesMainContractView() {
        return view;
    }
}
