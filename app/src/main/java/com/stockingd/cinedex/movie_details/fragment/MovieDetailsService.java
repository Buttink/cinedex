package com.stockingd.cinedex.movie_details.fragment;

import android.support.annotation.NonNull;

import com.stockingd.cinedex.PerFragment;
import com.stockingd.cinedex.tmdb.TheMovieDbService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

@PerFragment
public class MovieDetailsService {

    @NonNull private final TheMovieDbService theMovieDbService;

    private static final DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Inject
    public MovieDetailsService(@NonNull TheMovieDbService theMovieDbService) {
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<MovieDetailsModel> getModel(long movieId) {
        return theMovieDbService.getMovieDetails(movieId).map(details -> {
            int year;
            try {
                Date date = simpleFormat.parse(details.releaseDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
            } catch (ParseException e) {
                year = 0;
            }

            return MovieDetailsModel.create(details.backdropPath,
                                            details.posterPath,
                                            details.title,
                                            year,
                                            details.runtime,
                                            details.voteAverage,
                                            details.overview);
        });
    }
}
