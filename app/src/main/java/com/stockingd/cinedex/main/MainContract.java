package com.stockingd.cinedex.main;

public interface MainContract {

    interface View {
        void showMostPopular();
        void showHighestRated();
        void showFavorites();
        void closeDrawer();
    }

    interface Presenter {
        void onCreate(boolean restored);
        void onResume();
        void onPause();
    }
}
