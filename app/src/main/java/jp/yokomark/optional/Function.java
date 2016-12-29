package jp.yokomark.optional;

import android.support.annotation.NonNull;

/**
 * Backport of Function interface in Java8.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public interface Function<T, R> {
    R apply(T value);
}
