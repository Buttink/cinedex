package com.stockingd.cinedex.movie_details.review;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.google.auto.factory.AutoFactory;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.widget.BindingViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

@AutoFactory
public class MovieReviewViewHolder extends BindingViewHolder<MovieReviewModel> {

    @BindView(R.id.author) TextView author;
    @BindView(R.id.review) TextView review;

    public MovieReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(@NonNull MovieReviewModel model) {
        author.setText("- " + model.author()); // FIXME
        review.setText(model.review());
    }
}
