package com.stockingd.cinedex.movie_details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.stockingd.cinedex.favorite.FavoriteEntity;
import com.stockingd.cinedex.favorite.FavoritesRepository;
import com.stockingd.cinedex.movie_details.fragment.MovieDetailsModel;
import com.stockingd.cinedex.movie_details.review.item.MovieReviewModel;
import com.stockingd.cinedex.tmdb.TheMovieDbService;
import com.stockingd.cinedex.tmdb.json.GetMovieDetailsWithExtras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;

public class MovieDetailsActivityService {

    @NonNull private final FavoritesRepository favoritesRepository;
    @NonNull private final TheMovieDbService theMovieDbService;

    private static final DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Inject
    public MovieDetailsActivityService(@NonNull FavoritesRepository favoritesRepository,
                                       @NonNull TheMovieDbService theMovieDbService) {
        this.favoritesRepository = favoritesRepository;
        this.theMovieDbService = theMovieDbService;
    }

    public Observable<MovieDetailsActivityModel> getModel(long movieId) {
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

                   List<String> keys = Observable.from(details.videos.results)
                           .map(vr -> vr.key)
                           .toList()
                           .toBlocking()
                           .first();

                   List<MovieReviewModel> reviewModel = Observable.from(details.reviews.results)
                           .map(rr -> MovieReviewModel.create(
                                   rr.id,
                                   rr.author,
                                   rr.content.replace("\n", ""),
                                   rr.url))
                           .limit(3)
                           .toList()
                           .toBlocking()
                           .first();

                   boolean favorite = entity != null;
                   return MovieDetailsActivityModel.create(
                           keys,
                           reviewModel,
                           MovieDetailsModel.create(details.id,
                                                    details.backdropPath,
                                                    details.posterPath,
                                                    details.title,
                                                    year,
                                                    release,
                                                    details.runtime,
                                                    details.voteAverage / 10.0f,
                                                    details.overview,
                                                    favorite));
               });
    }

    @AutoValue
    public static abstract class ModelPair {

        public static ModelPair create(GetMovieDetailsWithExtras net,
                                       @Nullable FavoriteEntity disk) {
            return new AutoValue_MovieDetailsActivityService_ModelPair(net, disk);
        }

        public abstract GetMovieDetailsWithExtras net();
        @Nullable public abstract FavoriteEntity disk();
    }
}
