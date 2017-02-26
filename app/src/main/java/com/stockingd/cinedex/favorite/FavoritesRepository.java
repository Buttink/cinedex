package com.stockingd.cinedex.favorite;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.stockingd.cinedex.favorite.data.Favorite;
import com.stockingd.cinedex.favorite.data.FavoritesProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class FavoritesRepository {

    @NonNull private final ContentResolver contentResolver;

    private final Observable.Transformer<Cursor, List<FavoriteEntity>> cursorToFavoriteTransformer =
            cursorObservable -> cursorObservable.map(cursor -> {
                List<FavoriteEntity> favorites = new ArrayList<>();
                while (cursor.moveToNext()) {
                    favorites.add(FavoriteEntity.create(
                            cursor.getInt(cursor.getColumnIndex(Favorite._ID)),
                            cursor.getString(cursor.getColumnIndex(Favorite.TITLE)),
                            cursor.getFloat(cursor.getColumnIndex(Favorite.RATING)),
                            null));
                }
                return favorites;
            });

    @Inject
    public FavoritesRepository(@NonNull ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Observable<List<FavoriteEntity>> getFavoritesAsync() {
        return Observable.defer(() -> {
            return Observable.just(
                    contentResolver.query(
                            FavoritesProvider.Favorites.FAVORITES,
                            null,
                            null,
                            null,
                            null));
        })
                .compose(cursorToFavoriteTransformer);
    }

    public Observable<FavoriteEntity> queryByIdAsync(long movieId) {
        return Observable.defer(() -> {
            return Observable.just(
                    contentResolver.query(
                            FavoritesProvider.Favorites.withId(movieId),
                            null,
                            null,
                            null,
                            null));
        })
                .compose(cursorToFavoriteTransformer)
                .flatMap(Observable::from)
                .firstOrDefault(null);
    }

    public Observable<Uri> insert(FavoriteEntity entity) {
        return Observable.defer(() -> {
            ContentValues cv = new ContentValues(4);
            cv.put(Favorite._ID, entity.id());
            cv.put(Favorite.TITLE, entity.title());
            cv.put(Favorite.RATING, entity.rating());
            cv.put(Favorite.POSTER, entity.poster());
            return Observable.just(
                    contentResolver.insert(FavoritesProvider.Favorites.FAVORITES, cv));
        });
    }

    public Observable<Integer> delete(int id) {
        return Observable.defer(() -> {
            return Observable.just(
                    contentResolver.delete(FavoritesProvider.Favorites.withId(id), null, null));
        });
    }
}
