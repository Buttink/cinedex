package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class VideoResult {

    public String id;

    @JsonField(name = "iso_639_1")
    public String iso639_1;

    @JsonField(name = "iso_3166_1")
    public String iso3166_1;

    public String key;

    public String name;

    public String site;

    public int size;

    public String type;
}
