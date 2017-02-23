package com.stockingd.cinedex.favorite;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FavoriteEntity {

    public static FavoriteEntity create(int id,
                                        @NonNull String title,
                                        float rating,
                                        @Nullable byte[] poster) {
        return new AutoValue_FavoriteEntity(id, title, rating, poster);
    }

    public abstract int id();
    public abstract String title();
    public abstract float rating();
    @Nullable public abstract byte[] poster();
}
