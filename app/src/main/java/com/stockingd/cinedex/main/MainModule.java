package com.stockingd.cinedex.main;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.stockingd.cinedex.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @NonNull private final MainContract.View view;
    @NonNull private final AppCompatActivity activity;

    public MainModule(@NonNull MainContract.View view, @NonNull AppCompatActivity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Provides
    @PerActivity
    MainContract.View providesMainContractView() {
        return view;
    }

    @Provides
    @PerActivity
    AppCompatActivity providesActivity() {
        return activity;
    }
}
