package com.stockingd.cinedex.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseActivity;
import com.stockingd.cinedex.movie_list.MovieListFragment;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject MainPresenter presenter;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.drawer) View drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component().mainComponenet(new MainModule(this)).inject(this);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showMostPopular() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, new MovieListFragment())
                .commit();
    }

    @Override
    public void showHighestRated() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, new MovieListFragment())
                .commit();
    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawer(drawer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
