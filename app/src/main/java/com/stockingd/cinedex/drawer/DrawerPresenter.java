package com.stockingd.cinedex.drawer;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;
import com.stockingd.cinedex.drawer.event.CloseDrawerEvent;
import com.stockingd.cinedex.drawer.event.ShowHighestRatedEvent;
import com.stockingd.cinedex.drawer.event.ShowMostPopularEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

@PerFragment
public class DrawerPresenter implements DrawerContract.Presenter {

    @NonNull private final EventBus eventBus;

    @Inject
    public DrawerPresenter(@NonNull EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onMostPopularClicked() {
        eventBus.post(new ShowMostPopularEvent());
        eventBus.post(new CloseDrawerEvent());
    }

    @Override
    public void onHighestRatedClicked() {
        eventBus.post(new ShowHighestRatedEvent());
        eventBus.post(new CloseDrawerEvent());
    }
}
