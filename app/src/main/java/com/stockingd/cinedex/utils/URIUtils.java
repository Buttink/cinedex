package com.stockingd.cinedex.utils;

import java.net.URI;
import java.util.Optional;

public class URIUtils {

    public static Optional<URI> parse(String uri) {
        try {
            return Optional.of(URI.create(uri));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
