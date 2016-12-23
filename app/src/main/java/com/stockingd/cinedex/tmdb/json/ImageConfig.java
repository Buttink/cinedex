package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class ImageConfig {

    @JsonField(name = "base_url")
    public String baseUrl;

    @JsonField(name = "secure_base_url")
    public String secureBaseUrl;

    @JsonField(name = "backdrop_sizes")
    public List<String> backdropSizes;

    @JsonField(name = "logo_sizes")
    public List<String> logoSizes;

    @JsonField(name = "poster_sizes")
    public List<String> posterSizes;

    @JsonField(name = "profile_sizes")
    public List<String> profileSizes;

    @JsonField(name = "still_sizes")
    public List<String> stillSizes;
}
