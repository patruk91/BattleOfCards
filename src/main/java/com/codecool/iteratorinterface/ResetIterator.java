package com.codecool.iteratorinterface;

import com.codecool.gameelement.Card;

import java.util.Iterator;

public interface ResetIterator<T> extends Iterator {
    void resetIterator();

    @Override
    T next();
}
