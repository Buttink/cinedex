package com.stockingd.cinedex.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.functions.Func0;

public class Lazy<T> {

    @NonNull private final Func0<T> initializer;

    @Nullable private T object;

    public Lazy(@NonNull Func0<T> initializer) {
        this.initializer = initializer;
    }

    public T get() {
        if (object == null) {
            object = initializer.call();
        }

        return object;
    }
}
