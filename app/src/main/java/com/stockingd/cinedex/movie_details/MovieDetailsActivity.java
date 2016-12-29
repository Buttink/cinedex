package com.stockingd.cinedex.movie_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseActivity;
import com.stockingd.cinedex.tmdb.TheMovieDbImagePresenter;
import com.stockingd.cinedex.utils.Lazy;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MovieDetailsActivity extends BaseActivity implements BackdropView {

    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.backdrop) ImageView backdrop;

    @Inject TheMovieDbImagePresenter theMovieDbImagePresenter;

    @NonNull private Subscription backdropSubscription = Subscriptions.empty();

    public Lazy<MovieDetailsActivityComponent> activityComponent = new Lazy<>(() -> {
        return component().movieDetailsActivityComponent(new MovieDetailsActivityModule(this));
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent.get().inject(this);
        setContentView(R.layout.movie_details_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setToolbarTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
        int color = getResources().getColor(R.color.colorPrimary);
        collapsingToolbarLayout.setContentScrimColor(color);
    }

    @Override
    public void setBackdrop(@NonNull String backdropPath) {
        compositeSubscription.remove(backdropSubscription);
        backdropSubscription = theMovieDbImagePresenter.loadBackdrop(backdropPath, backdrop)
                    .subscribe();
        compositeSubscription.add(backdropSubscription);
    }
}
