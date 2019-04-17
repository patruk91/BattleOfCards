package com.codecool.gameelement;

import java.util.ArrayList;
import java.util.List;

public class Pile {
    private List<Card> cards;
    private Player containingPlayer;

    public Pile() {
        cards = new ArrayList<>();
    }

    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getContainingPlayer() {
        return containingPlayer;
    }

    public void setContainingPlayer(Player containingPlayer) {
        this.containingPlayer = containingPlayer;
    }
}
