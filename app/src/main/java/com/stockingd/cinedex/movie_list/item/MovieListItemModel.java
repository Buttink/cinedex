package com.stockingd.cinedex.movie_list.item;

import com.google.auto.value.AutoValue;
import com.github.dmstocking.optional.java.util.Optional;
import com.stockingd.cinedex.widget.Identifiable;

@AutoValue
public abstract class MovieListItemModel implements Identifiable<MovieListItemModel> {

    public static MovieListItemModel create(long id,
                                            Optional<String> posterPath,
                                            String title,
                                            String rating) {
        return new AutoValue_MovieListItemModel(id, posterPath, title, rating);
    }

    public abstract long id();
    public abstract Optional<String> posterPath();
    public abstract String title();
    public abstract String rating();

    @Override
    public boolean identify(MovieListItemModel other) {
        return id() == other.id();
    }
}
