package com.stockingd.cinedex.movie_details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.dmstocking.optional.java.util.Optional;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseActivity;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsFragment;
import com.stockingd.cinedex.movie_details.review.MovieReviewFragment;
import com.stockingd.cinedex.movie_details.trailer.TrailerViewPager;

import java.net.URI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsActivityContract.View {

    public static final String MOVIE_DETAIL_TAG = "MOVIE_DETAIL_TAG";
    public static final String MOVIE_REVIEW_TAG = "MOVIE_REVIEW_TAG";

    public static final String EXTRA_ARGS = "EXTRA_ARGS";

    @Inject MovieDetailsActivityPresenter presenter;

    @BindView(R.id.root) View root;
    @BindView(R.id.trailer_pager) ViewPager trailerViewPager;
    @BindView(R.id.refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.indicator) CircleIndicator indicator;

    @NonNull private Optional<Snackbar> snackbar = Optional.empty();

    private TrailerViewPager adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        MovieDetailsActivityArgs args;
        if (intent != null) {
            args = intent.getParcelableExtra(EXTRA_ARGS);
        } else {
            finish();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MOVIE_DETAIL_TAG);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.movie_details_fragment_container,
                            MovieDetailsFragment.create(args.movieId()),
                            MOVIE_DETAIL_TAG)
                    .commit();
        }

        fragment = fragmentManager.findFragmentByTag(MOVIE_REVIEW_TAG);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.movie_review_fragment_container,
                            MovieReviewFragment.create(args.movieId()),
                            MOVIE_REVIEW_TAG)
                    .commit();
        }

        component().movieDetailsActivityComponent(new MovieDetailsActivityModule(this, args))
                .inject(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.onRefresh();
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        adapter = new TrailerViewPager(getSupportFragmentManager());
        trailerViewPager.setAdapter(adapter);
        indicator.setViewPager(trailerViewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        presenter.onResume();
    }

    @Override
    public void onModel(MovieDetailsActivityModel model) {
        swipeRefreshLayout.setRefreshing(false);
        snackbar.ifPresent(s -> {
            s.dismiss();
        });
        adapter.swapModel(model.keys());
    }

    @Override
    public void onError() {
        swipeRefreshLayout.setRefreshing(false);
        snackbar.ifPresent(s -> {
            s.dismiss();
        });
        Snackbar snackbar = Snackbar.make(root, "Can't fetch movie details.", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
        this.snackbar = Optional.of(snackbar);
    }

    @Override
    public void onLaunchReviewEvent(URI reviewUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(reviewUri.toString()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
