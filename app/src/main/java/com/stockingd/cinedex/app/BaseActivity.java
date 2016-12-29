package com.stockingd.cinedex.app;

import android.support.v7.app.AppCompatActivity;

import com.stockingd.cinedex.CinedexApplication;
import com.stockingd.cinedex.CinedexComponent;

import javax.inject.Inject;
import javax.inject.Named;

import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    @Inject @Named("activity") protected CompositeSubscription compositeSubscription;

    protected CinedexApplication application() {
        return ((CinedexApplication)this.getApplicationContext());
    }

    protected CinedexComponent component() {
        return application().component();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeSubscription.clear();
    }
}
