package com.stockingd.cinedex.movie_details.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseFragment;
import com.stockingd.cinedex.movie_details.BackdropView;
import com.stockingd.cinedex.movie_details.MovieDetailsActivity;
import com.stockingd.cinedex.tmdb.TheMovieDbImagePresenter;
import com.github.dmstocking.optional.java.util.Optional;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsContract.View {

    public static final String EXTRA_ARGS = "EXTRA_ARGS";

    private View root;
    @BindView(R.id.refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.year) TextView year;
    @BindView(R.id.runtime) TextView runtime;
    @BindView(R.id.rating) TextView rating;
    @BindView(R.id.overview) TextView overview;

    @Inject MovieDetailsPresenter presenter;
    @Inject TheMovieDbImagePresenter theMovieDbImagePresenter;
    @Inject BackdropView backdropView;

    @NonNull private Subscription backdropSubscription = Subscriptions.empty();
    @NonNull private Subscription posterSubscriptions = Subscriptions.empty();

    @NonNull private Optional<Snackbar> snackbar = Optional.empty();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Intent intent = getActivity().getIntent();
        MovieDetailsFragmentArgs args = null;
        if (intent != null) {
            args = intent.getParcelableExtra(EXTRA_ARGS);
        }
        MovieDetailsFragmentArgs finalArgs = args;
        component().ifPresent(c -> {
            if (finalArgs != null && getActivity() instanceof MovieDetailsActivity) {
                ((MovieDetailsActivity) getActivity()).activityComponent.get()
                        .movieDetailsFragmentComponent(new MovieDetailsModule(this, finalArgs))
                        .inject(this);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        root = view;
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            presenter.onResume();
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setRefreshing(true);
        presenter.onResume();
    }

    @Override
    public void onModel(MovieDetailsModel model) {
        snackbar.ifPresent(s -> {
            s.dismiss();
        });
        swipeRefreshLayout.setRefreshing(false);
        Resources resources = getResources();
        backdropSubscription.unsubscribe();
        model.backdropPath().ifPresent(backdropPath -> {
            backdropView.setBackdrop(backdropPath);
        });
        posterSubscriptions.unsubscribe();
        posterSubscriptions = model.posterPath().map(posterPath -> {
            return theMovieDbImagePresenter.loadPoster(posterPath, poster).subscribe();
        }).orElse(Subscriptions.empty());
        ((MovieDetailsActivity) getActivity()).setToolbarTitle(model.title());
        year.setText(String.valueOf(model.year()));
        runtime.setText(resources.getString(R.string.movie_details_runtime, model.runtime()));
        rating.setText(resources.getString(R.string.movie_details_rating, model.rating()));
        overview.setText(model.overview());
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
}
