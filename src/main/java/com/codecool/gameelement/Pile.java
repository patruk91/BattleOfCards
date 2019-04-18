package com.codecool.gameelement;

import java.util.ArrayList;
import java.util.List;

public class Pile {
    private List<Card> cards;
    private User containingUser;

    public Pile() {
        cards = new ArrayList<>();
    }

    public void setContainingUser(User containingUser) {
        this.containingUser = containingUser;
    }

    public User getContainingUser() {
        return containingUser;
    }

    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

    public void addCard(Card card) {
        cards.add(0, card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeLastCard() {
        cards.remove(cards.size()-1);
    }


    public List<Card> getCards() {
        return cards;
    }
}
