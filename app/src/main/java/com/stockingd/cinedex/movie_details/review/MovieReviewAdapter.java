package com.stockingd.cinedex.movie_details.review;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.widget.BindingListAdapter;

import javax.inject.Inject;

@ViewScope
public class MovieReviewAdapter extends BindingListAdapter<MovieReviewModel, MovieReviewViewHolder> {

    @NonNull private final MovieReviewViewHolderFactory factory;

    @Inject
    public MovieReviewAdapter(@NonNull MovieReviewViewHolderFactory factory) {
        this.factory = factory;
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return factory.create(inflater.inflate(R.layout.movie_review_list_item, parent, false));
    }
}
