package com.stockingd.cinedex.movie_list.item;

import com.google.auto.value.AutoValue;
import com.stockingd.cinedex.widget.Identifiable;

import java.util.Optional;

@AutoValue
public abstract class MovieListItemModel implements Identifiable {

    public static MovieListItemModel create(long id,
                                            Optional<String> posterPath,
                                            String title,
                                            String summary,
                                            String rating) {
        return new AutoValue_MovieListItemModel(id, posterPath, title, summary, rating);
    }

    public abstract long id();
    public abstract Optional<String> posterPath();
    public abstract String title();
    public abstract String summary();
    public abstract String rating();

}
