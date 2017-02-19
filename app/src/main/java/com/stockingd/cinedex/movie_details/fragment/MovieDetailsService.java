package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.ViewScope;
import com.stockingd.cinedex.tmdb.TheMovieDbService;

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

    @NonNull private final TheMovieDbService theMovieDbService;

    private static final DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Inject
    public MovieDetailsService(@NonNull TheMovieDbService theMovieDbService) {
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<MovieDetailsModel> getModel(long movieId) {
        return theMovieDbService.getMovieDetailsWithExtras(movieId).map(details -> {
            Date release = new Date(0);
            int year = 0;
            try {
                release = simpleFormat.parse(details.releaseDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(release);
                year = calendar.get(Calendar.YEAR);
            } catch (ParseException ignore) {
            }

            return MovieDetailsModel.create(details.backdropPath,
                                            details.posterPath,
                                            details.title,
                                            year,
                                            release,
                                            details.runtime,
                                            details.voteAverage / 10.0f,
                                            details.overview);
        });
    }
}
