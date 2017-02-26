package com.stockingd.cinedex.favorite.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.stockingd.cinedex.favorite.data.Favorite;
import java.lang.Override;
import java.lang.String;

public class FavoritesDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

//@formatter:off
    public static final String FAVORITES =
"CREATE TABLE favorites (" +
    Favorite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
    Favorite.TITLE + " TEXT NOT NULL," +
    Favorite.RATING + " REAL NOT NULL," +
    Favorite.POSTER + " BLOB" +
")";

    public static final String DROP_TABLES =
"DROP TABLE favorites";
//@formatter:on

    public FavoritesDatabase(Context context) {
        super(context.getApplicationContext(), "favoritesDatabase.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLES);
        onCreate(db);
    }
}

