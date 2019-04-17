package com.codecool.comparator;

import com.codecool.gameelement.Card;

import java.util.Comparator;

public class TopSpeedComparator implements Comparator<Card> {
    @Override
    public int compare(Card myCard, Card opponentCard) {
        double result = myCard.getTopSpeed() - opponentCard.getTopSpeed();
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
