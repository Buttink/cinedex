package com.stockingd.cinedex.movie_list.item;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import com.squareup.picasso.Picasso;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.movie_list.MovieListPresenter;
import com.stockingd.cinedex.tmdb.TheMovieDbImageService;
import com.stockingd.cinedex.widget.BindingViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Subscribers;
import rx.subscriptions.Subscriptions;

@AutoFactory
public class MovieListViewHolder extends BindingViewHolder<MovieListItemModel> {

    private static final String TAG = "MovieListViewHolder";

    @BindView(R.id.item) View item;
    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.summary) TextView summary;
    @BindView(R.id.rating) TextView rating;

    @NonNull private final MovieListPresenter movieListPresenter;
    @NonNull private final Picasso picasso;
    @NonNull private final TheMovieDbImageService theMovieDbImageService;

    @NonNull private Subscription posterSubscription = Subscriptions.empty();

    public MovieListViewHolder(View itemView,
                               @Provided @NonNull MovieListPresenter movieListPresenter,
                               @Provided @NonNull Picasso picasso,
                               @Provided @NonNull TheMovieDbImageService theMovieDbImageService) {
        super(itemView);
        this.movieListPresenter = movieListPresenter;
        this.picasso = picasso;
        this.theMovieDbImageService = theMovieDbImageService;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(MovieListItemModel data) {
        itemView.setOnClickListener(v -> movieListPresenter.onItemClicked(data.id()));
        posterSubscription.unsubscribe();
        posterSubscription = data.posterPath().map(pp -> {
            return theMovieDbImageService.imageUri(poster, pp)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(uri -> {
                        picasso.load(uri)
                                .fit()
                                .into(poster);
                    }, error -> {
                        Log.e(TAG, "Error Loading Poster", error);
                    });
        }).orElse(Subscribers.empty());
        title.setText(data.title());
        summary.setText(data.summary());
        rating.setText(data.rating());
    }
}
