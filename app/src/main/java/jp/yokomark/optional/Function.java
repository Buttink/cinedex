package jp.yokomark.optional;

/**
 * Backport of Function interface in Java8.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public interface Function<T, R> {
    R apply(T value);
}
