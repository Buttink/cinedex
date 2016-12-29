package com.stockingd.cinedex.utils;

import java.net.URI;

import jp.yokomark.optional.Optional;

public class URIUtils {

    public static Optional<URI> parse(String uri) {
        try {
            return Optional.of(URI.create(uri));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
