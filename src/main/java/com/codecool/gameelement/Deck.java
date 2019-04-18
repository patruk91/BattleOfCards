package com.codecool.gameelement;

import com.codecool.iteratorinterface.ResetIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private List<Card> cards;
    private ResetIterator<Card> deckIterator;

    public Deck(List<Card> cards) {
        this.cards = cards;
        deckIterator = new DeckIterator();
    }

    public List<Card> getDeck() {
        return cards;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public ResetIterator<Card> getDeckIterator() {
        return deckIterator;
    }

    private class DeckIterator implements ResetIterator<Card> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < cards.size();
        }

        @Override
        public Card next() throws NoSuchElementException {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return cards.get(index++);
        }

        public void resetIterator() {
            index = 0;
        }
    }
}
