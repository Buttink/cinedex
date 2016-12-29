package com.stockingd.cinedex.app;

import android.support.v4.app.Fragment;

import com.stockingd.cinedex.CinedexApplication;
import com.stockingd.cinedex.CinedexComponent;

import javax.inject.Inject;
import javax.inject.Named;

import jp.yokomark.optional.Optional;
import rx.subscriptions.CompositeSubscription;

public class BaseFragment extends Fragment {

    @Inject @Named("fragment") protected CompositeSubscription compositeSubscription;

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
