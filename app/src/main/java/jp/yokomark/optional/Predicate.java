package jp.yokomark.optional;

/**
 * Backport of Predicate interface in Java8.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public APIs
public interface Predicate<T> {
    boolean test(T value);
}
