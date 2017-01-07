package com.stockingd.cinedex.utils;

import com.github.dmstocking.optional.java.util.Optional;

import java.net.URI;

public class URIUtils {

    public static Optional<URI> parse(String uri) {
        try {
            return Optional.of(URI.create(uri));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
