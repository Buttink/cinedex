package com.stockingd.cinedex.favorite.data.impl;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;

public class FavoritesProvider extends ContentProvider {

    public static final String AUTHORITY = "com.stockingd.cinedex.favorite.data.FavoritesProvider";

    private static final int FAVORITES = 0;
    private static final int FAVORITE_ID = 1;

    private final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private ContentResolver contentResolver;
    private SQLiteOpenHelper database;

    @Override
    public boolean onCreate() {
        matcher.addURI(AUTHORITY, "favorite", FAVORITES);
        matcher.addURI(AUTHORITY, "favorite/#", FAVORITE_ID);
        database = new FavoritesDatabase(getContext());
        contentResolver = getContext().getContentResolver();
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch(matcher.match(uri)) {
            case FAVORITES: {
                return "vnd.android.cursor.dir/favorite";
            }
            case FAVORITE_ID: {
                return "vnd.android.cursor.item/favorite";
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        final SQLiteDatabase db = database.getReadableDatabase();
        switch(matcher.match(uri)) {
            case FAVORITES: {
                Cursor cursor = db.query("Favorites", null, null, null, null, null, "title ASC");
                cursor.setNotificationUri(contentResolver, uri);
                return cursor;
            }
            case FAVORITE_ID: {
                String id = uri.getPathSegments().get(1);
                Cursor cursor = db.query("Favorites",
                                         null,
                                         "_id = ?",
                                         new String[] {id},
                                         null,
                                         null,
                                         "title ASC");
                cursor.setNotificationUri(contentResolver, uri);
                return cursor;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch(matcher.match(uri)) {
            case FAVORITES: {
                final long id = db.insertOrThrow("favorites", null, values);
                contentResolver.notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case FAVORITE_ID: {
                final long id = db.insertOrThrow("favorites", null, values);
                contentResolver.notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String where, String[] whereArgs) {
        throw new IllegalArgumentException("update is not supported");
    }

    @Override
    public int delete(@NonNull Uri uri, String where, String[] whereArgs) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch(matcher.match(uri)) {
            case FAVORITES: {
                final int count = db.delete("Favorites", null, null);
                contentResolver.notifyChange(uri, null);
                return count;
            }
            case FAVORITE_ID: {
                String id = uri.getPathSegments().get(1);
                final int count = db.delete("Favorites", "_id = ?", new String[] {id});
                contentResolver.notifyChange(uri, null);
                return count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }
}

