package com.stockingd.cinedex.tmdb.json;

import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class GenresObject {

    public String name;

    public int id;
}
