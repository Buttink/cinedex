package com.stockingd.cinedex.widget;

public interface Identifiable<T> {

    boolean identify(T other);
}
