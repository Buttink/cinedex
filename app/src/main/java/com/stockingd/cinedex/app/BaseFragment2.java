package com.stockingd.cinedex.app;

import android.support.v4.app.Fragment;

import com.github.dmstocking.optional.java.util.Optional;
import com.stockingd.cinedex.CinedexApplication;
import com.stockingd.cinedex.CinedexComponent;

import javax.inject.Inject;
import javax.inject.Named;

import rx.subscriptions.CompositeSubscription;

public class BaseFragment2 extends Fragment {

    protected Optional<CinedexApplication> application() {
        return Optional.ofNullable(getActivity()).map(a -> {
                return (CinedexApplication)a.getApplicationContext();
        });
    }

    protected Optional<CinedexComponent> component() {
        return application().map(CinedexApplication::component);
    }
}
