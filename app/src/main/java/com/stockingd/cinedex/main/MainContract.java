package com.stockingd.cinedex.main;

public interface MainContract {

    interface View {
        void showMostPopular();
        void showHighestRated();
        void closeDrawer();
    }

    interface Presenter {
        void onCreate();
        void onResume();
        void onPause();
    }
}
