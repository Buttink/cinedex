package com.stockingd.cinedex;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    @NonNull private final Application application;

    public AndroidModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ContentResolver providesContentResolver() {
        return application.getContentResolver();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources providesResources() {
        return application.getResources();
    }
}
