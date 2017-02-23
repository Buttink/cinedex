package com.stockingd.cinedex.drawer;

public interface DrawerContract {

    interface View {
    }

    interface Presenter {
        void onMostPopularClicked();
        void onHighestRatedClicked();
        void onFavoritesClicked();
    }
}
