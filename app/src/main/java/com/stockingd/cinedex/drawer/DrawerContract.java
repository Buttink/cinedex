package com.stockingd.cinedex.drawer;

/**
 * Created by buttink on 12/23/16.
 */

public interface DrawerContract {

    interface View {
    }

    interface Presenter {
        void onMostPopularClicked();
        void onHighestRatedClicked();
    }
}
