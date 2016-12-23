package com.stockingd.cinedex;

import android.support.v4.app.Fragment;

import java.util.Optional;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment {

    @Inject CompositeSubscription compositeSubscription;

    protected Optional<CinedexApplication> application() {
        return Optional.ofNullable(getActivity()).map(a -> {
                return (CinedexApplication)a.getApplicationContext();
        });
    }

    protected Optional<CinedexComponent> component() {
        return application().map(CinedexApplication::component);
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeSubscription.clear();
    }
}
