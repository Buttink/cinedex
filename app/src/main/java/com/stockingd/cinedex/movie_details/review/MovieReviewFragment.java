package com.stockingd.cinedex.movie_details.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stockingd.cinedex.R;
import com.stockingd.cinedex.app.BaseFragment2;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewFragment extends BaseFragment2 implements MovieReviewContract.View {

    public static final String ARGS_KEY = "data";

    @BindView(R.id.movie_review_list) RecyclerView reviewList;

    @Inject MovieReviewPresenter presenter;
    @Inject MovieReviewAdapter adapter;

    public static MovieReviewFragment create(long movieId) {
        MovieReviewFragment fragment = new MovieReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_KEY, MovieReviewArgs.create(movieId));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieReviewArgs args = getArguments().getParcelable(ARGS_KEY);
        component().ifPresent(component ->
                component.movieReviewFragmentComponent(new MovieReviewModule(this, args))
                        .inject(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_review_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setAutoMeasureEnabled(true);
        reviewList.setLayoutManager(layout);
        reviewList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onModel(List<MovieReviewModel> model) {
        adapter.updateModel(model);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }
}
