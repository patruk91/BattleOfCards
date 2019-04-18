package com.codecool.comparator;

import com.codecool.gameelement.Card;

import java.util.Comparator;

public class CapacityComparator implements Comparator<Card> {
    @Override
    public int compare(Card myCard, Card oponentCard) {
        return myCard.getCapacity() - oponentCard.getCapacity();
    }
}
