package com.stockingd.cinedex.utils;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.stockingd.cinedex.PerActivity;

import javax.inject.Inject;

@PerActivity
public class ActivityUtils {

    @NonNull private final AppCompatActivity appCompatActivity;

    @Inject
    public ActivityUtils(@NonNull AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
