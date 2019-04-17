package com.codecool.gameelement;

import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getDeck() {
        return cards;
    }

    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getDeck());
    }
}
