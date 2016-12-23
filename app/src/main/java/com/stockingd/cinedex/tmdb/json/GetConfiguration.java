package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class GetConfiguration {

    public ImageConfig images;

    @JsonField(name = "change_keys")
    public List<String> changeKeys;
}
