package com.stockingd.cinedex.drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawerFragment extends BaseFragment implements DrawerContract.View {

    @Inject DrawerPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        component().ifPresent(c -> {
            c.drawerComponent(new DrawerModule(this)).inject(this);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drawer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.most_popular)
    public void onMostPopularClicked() {
        presenter.onMostPopularClicked();
    }

    @OnClick(R.id.highest_rated)
    public void onHighestRatedClicked() {
        presenter.onHighestRatedClicked();
    }
}
