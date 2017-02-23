package com.stockingd.cinedex.movie_details.review.item;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.github.dmstocking.optional.java.util.Optional;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import com.stockingd.cinedex.R;
import com.stockingd.cinedex.widget.BindingViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@AutoFactory
public class MovieReviewViewHolder
        extends BindingViewHolder<MovieReviewModel>{

    @NonNull private final EventBus bus;

    @BindView(R.id.author) TextView author;
    @BindView(R.id.review) TextView review;

    @NonNull private Optional<MovieReviewModel> model = Optional.empty();

    public MovieReviewViewHolder(@NonNull View itemView,
                                 @Provided @NonNull EventBus bus) {
        super(itemView);
        this.bus = bus;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(@NonNull MovieReviewModel model) {
        this.model = Optional.of(model);
        author.setText(model.author());
        review.setText(model.review());
    }

    @OnClick(R.id.card)
    public void onCardClicked() {
        model.ifPresent(model -> {
            URI reviewUri = URI.create(model.url());
            bus.post(LaunchReviewEvent.create(reviewUri));
        });
    }
}
