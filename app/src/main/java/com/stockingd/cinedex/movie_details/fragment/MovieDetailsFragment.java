package com.stockingd.cinedex.movie_details.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.sackcentury.shinebuttonlib.ShineView;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseFragment2;
import com.stockingd.cinedex.movie_details.MovieDetailsActivity;
import com.stockingd.cinedex.tmdb.TheMovieDbImagePresenter;

import java.text.DateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MovieDetailsFragment extends BaseFragment2 implements MovieDetailsContract.View {

    public static final String ARGS_KEY = "args";
    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.runtime) TextView runtime;
    @BindView(R.id.release_date) TextView releaseDate;
    @BindView(R.id.rating) RatingBar rating;
    @BindView(R.id.overview) TextView overview;
    @BindView(R.id.favorite) ShineButton favorite;

    @Inject MovieDetailsPresenter presenter;
    @Inject TheMovieDbImagePresenter theMovieDbImagePresenter;

    @NonNull private Subscription posterSubscriptions = Subscriptions.empty();

    public static MovieDetailsFragment create(long movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_KEY, MovieDetailsFragmentArgs.create(movieId));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        MovieDetailsFragmentArgs args = arguments.getParcelable(ARGS_KEY);

        component().ifPresent(c -> {
            c.movieDetailsFragmentComponent(new MovieDetailsFragmentModule(this, args))
                    .inject(this);
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
        ButterKnife.bind(this, view);
        title.setSelected(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onModel(MovieDetailsModel model) {
        Resources resources = getResources();
        posterSubscriptions.unsubscribe();
        posterSubscriptions = model.posterPath()
                .map(posterPath -> {
                    return theMovieDbImagePresenter.loadPoster(posterPath, poster)
                            .subscribe();
        }).orElse(Subscriptions.empty());
        ((MovieDetailsActivity) getActivity()).setToolbarTitle(model.title());
        title.setText(resources.getString(R.string.movie_details_title, model.title(), model.year()));
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        model.release().ifPresentOrElse(
                date -> releaseDate.setText(df.format(date)),
                () -> releaseDate.setText(""));
        runtime.setText(resources.getString(R.string.movie_details_runtime, model.runtime()));
        rating.setRating(model.rating() * 5);
        overview.setText(model.overview());
        favorite.setChecked(model.favorite());
    }

    @OnClick(R.id.favorite)
    public void onFavoriteClicked() {
        presenter.onFavoriteClicked(favorite.isChecked());
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
