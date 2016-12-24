package com.stockingd.cinedex.utils;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Units {

    @NonNull private final Resources resources;

    @Inject
    public Units(@NonNull Resources resources) {
        this.resources = resources;
    }

    public float toDp(int pixels) {
        return pixels / resources.getDisplayMetrics().density;
    }

    public float toPx(float dp) {
        return dp * resources.getDisplayMetrics().density;
    }
}
