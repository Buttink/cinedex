package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.favorite.FavoriteEntity;
import com.stockingd.cinedex.favorite.FavoritesRepository;
import com.stockingd.cinedex.tmdb.TheMovieDbService;
import com.stockingd.cinedex.tmdb.json.GetMovieDetailsWithExtras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

@ViewScope
public class MovieDetailsService {

    @NonNull private final FavoritesRepository favoritesRepository;
    @NonNull private final TheMovieDbService theMovieDbService;

    private static final DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Inject
    public MovieDetailsService(@NonNull FavoritesRepository favoritesRepository,
                               @NonNull TheMovieDbService theMovieDbService) {
        this.favoritesRepository = favoritesRepository;
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<MovieDetailsModel> getModel(long movieId) {
        return Observable.combineLatest(theMovieDbService.getMovieDetailsWithExtras(movieId),
                                        favoritesRepository.queryByIdAsync(movieId),
                                        ModelPair::create)
                .map(data -> {
                    GetMovieDetailsWithExtras details = data.net();
                    FavoriteEntity entity = data.disk();
                    Date release = new Date(0);
                    int year = 0;
                    try {
                        release = simpleFormat.parse(details.releaseDate);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(release);
                        year = calendar.get(Calendar.YEAR);
                    } catch (ParseException ignore) {
                    }

                    boolean favorite = entity != null;
                    return MovieDetailsModel.create(details.id,
                                                    details.backdropPath,
                                                    details.posterPath,
                                                    details.title,
                                                    year,
                                                    release,
                                                    details.runtime,
                                                    details.voteAverage / 10.0f,
                                                    details.overview,
                                                    favorite);
                });
    }

    @AutoValue
    public static abstract class ModelPair {

        public static ModelPair create(GetMovieDetailsWithExtras net,
                                       @Nullable FavoriteEntity disk) {
            return new AutoValue_MovieDetailsService_ModelPair(net, disk);
        }

        public abstract GetMovieDetailsWithExtras net();
        @Nullable public abstract FavoriteEntity disk();
    }
}
