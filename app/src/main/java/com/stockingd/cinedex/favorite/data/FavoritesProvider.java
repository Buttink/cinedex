package com.stockingd.cinedex.favorite.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/*
 * The generated classes by schematic are unused and instead this is just used as a place to store
 * uris.
 */
@ContentProvider(authority = FavoritesProvider.AUTHORITY, database = FavoritesDatabase.class)
public final class FavoritesProvider {

    public static final String AUTHORITY = "com.stockingd.cinedex.favorite.data.FavoritesProvider";

    @TableEndpoint(table = FavoritesDatabase.FAVORITES)
    public static class Favorites {

        @ContentUri(
                path = "favorite",
                type = "vnd.android.cursor.dir/favorite",
                defaultSort = Favorite.TITLE + " ASC")
        public static final Uri FAVORITES = Uri.parse("content://" + AUTHORITY + "/favorite");

        @InexactContentUri(
                path = "favorite/#",
                name = "LIST_ID",
                type = "vnd.android.cursor.item/favorite",
                whereColumn = Favorite._ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return Uri.parse("content://" + AUTHORITY + "/favorite/" + String.valueOf(id));
        }
    }
}
