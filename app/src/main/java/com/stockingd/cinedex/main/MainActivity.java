package com.stockingd.cinedex.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseActivity;
import com.stockingd.cinedex.movie_list.MovieListFragment;
import com.stockingd.cinedex.movie_list.MovieListFragmentArgs;

import javax.inject.Inject;

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
        swapIn(MovieListFragmentArgs.Mode.MostPopular);
    }

    @Override
    public void showHighestRated() {
        swapIn(MovieListFragmentArgs.Mode.HighestRated);
    }

    private void swapIn(MovieListFragmentArgs.Mode mode) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putParcelable(MovieListFragment.EXTRA_ARGUMENTS, MovieListFragmentArgs.create(mode));
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, fragment)
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
