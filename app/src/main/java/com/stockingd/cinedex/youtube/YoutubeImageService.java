package com.stockingd.cinedex.youtube;

import android.net.Uri;
import android.widget.ImageView;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class YoutubeImageService {

    @Inject
    public YoutubeImageService() {
    }

    public Observable<Uri> getImageUriFor(ImageView imageView, String key) {
        return Observable.just(key)
                .map(k -> Uri.parse("http://img.youtube.com/vi/" + key + "/0.jpg"));
    }
}
