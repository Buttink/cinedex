package com.stockingd.cinedex.movie_details;

import com.google.auto.value.AutoValue;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsModel;
import com.stockingd.cinedex.movie_details.review.MovieReviewModel;

import java.util.List;

@AutoValue
public abstract class MovieDetailsActivityModel {

    public static MovieDetailsActivityModel create(List<String> keys,
                                                   List<MovieReviewModel> reviewModel,
                                                   MovieDetailsModel detailModel) {
        return new AutoValue_MovieDetailsActivityModel(keys, reviewModel, detailModel);
    }

    public abstract List<String> keys();
    public abstract List<MovieReviewModel> reviewModel();
    public abstract MovieDetailsModel detailsModel();
}
