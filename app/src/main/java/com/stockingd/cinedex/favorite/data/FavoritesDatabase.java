package com.stockingd.cinedex.favorite.data;

import net.simonvt.schematic.annotation.Table;

@net.simonvt.schematic.annotation.Database(version = FavoritesDatabase.VERSION)
public final class FavoritesDatabase {

    public static final int VERSION = 2;

    @Table(Favorite.class) public static final String FAVORITES = "favorites";
}