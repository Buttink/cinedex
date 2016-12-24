package com.stockingd.cinedex.tmdb;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.stockingd.cinedex.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

@Module
public class TheMovieDbModule {

    @Singleton
    @Provides
    TheMovieDbService providesTheMovieDbService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl url = original.url()
                            .newBuilder()
                            .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
                            .build();
                    Request request = original
                            .newBuilder()
                            .url(url)
                            .build();
                    return chain.proceed(request);
                })
                .build();
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build()
                .create(TheMovieDbService.class);
    }
}
