package com.stockingd.cinedex.main;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.drawer.event.ShowFavoritesEvent;
import com.stockingd.cinedex.drawer.event.ShowHighestRatedEvent;
import com.stockingd.cinedex.drawer.event.ShowMostPopularEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

@ViewScope
public class MainPresenter implements MainContract.Presenter {

    public enum View { MOST_POPULAR, HIGHEST_RATED, FAVORITES }

    @NonNull private final MainContract.View view;
    @NonNull private final EventBus eventBus;

    @Inject
    public MainPresenter(@NonNull MainContract.View view, @NonNull EventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;
    }

    @Override
    public void onCreate(boolean restored) {
        if (!restored) {
            view.showMostPopular();
        }
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Subscribe
    public void onShowMostPopular(ShowMostPopularEvent event) {
        view.showMostPopular();
        view.closeDrawer();
    }

    @Subscribe
    public void onHighestRatedPopular(ShowHighestRatedEvent event) {
        view.showHighestRated();
        view.closeDrawer();
    }

    @Subscribe
    public void onShowFavories(ShowFavoritesEvent event) {
        view.showFavorites();
        view.closeDrawer();
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }
}
