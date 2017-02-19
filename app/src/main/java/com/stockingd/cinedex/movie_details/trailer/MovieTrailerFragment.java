package com.stockingd.cinedex.movie_details.trailer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.dmstocking.optional.java.util.Optional;
import com.squareup.picasso.Picasso;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseFragment2;
import com.stockingd.cinedex.movie_details.MovieDetailsActivityContract;
import com.stockingd.cinedex.youtube.YoutubeImageService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MovieTrailerFragment extends BaseFragment2 implements MovieTrailerContract.View {

    public static final String ARGS_KEY = "args";

    @Inject MovieTrailerPresenter presenter;
    @Inject YoutubeImageService youtubeImageService;
    @Inject Picasso picasso;

    @BindView(R.id.movie_trailer_image) ImageView trailerImage;
    private Subscription youtubeSubscription = Subscriptions.empty();
    private Optional<String> key = Optional.empty();

    public static MovieTrailerFragment create(String key) {
        MovieTrailerFragment fragment = new MovieTrailerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_KEY, MovieTrailerArgs.create(key));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MovieTrailerArgs args = getArguments().getParcelable(ARGS_KEY);
        component().ifPresent(component -> component.movieTrailerFragmentComponent(new MovieTrailerModule(this, args))
                .inject(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_trailer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onModel(String key) {
        this.key = Optional.of(key);
        youtubeSubscription.unsubscribe();
        youtubeSubscription = youtubeImageService.getImageUriFor(trailerImage, key)
                .subscribe(uri -> {
                    picasso.load(uri)
                            .fit()
                            .centerCrop()
                            .into(trailerImage);
                });
    }

    @OnClick(R.id.movie_trailer_image)
    public void onMovieTrailerClicked() {
        presenter.onTrailerClicked();
    }

    @Override
    public void launchTrailer() {
        key.map(key -> Uri.parse("https://www.youtube.com/watch?v=" + key))
                .map(uri -> new Intent(Intent.ACTION_VIEW, uri))
                .filter(intent -> intent.resolveActivity(getActivity().getPackageManager()) != null)
                .ifPresentOrElse(this::startActivity,
                        () -> {
                            Snackbar snackbar = Snackbar.make(
                                    trailerImage,
                                    "No application to play trailer found",
                                    Snackbar.LENGTH_LONG);
                            snackbar.show();
                        });
    }

    @Override
    public void onPause() {
        super.onPause();
        youtubeSubscription.unsubscribe();
    }
}
