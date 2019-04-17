package com.codecool.gameelement;

import com.codecool.dao.CardDao;
import com.codecool.dao.CardDaoXML;

import java.util.Iterator;

public class Dealer extends User{
    private static final int AMOUNT_OF_CARDS_PER_PLAYER = 5;
    private Deck deck;
    private Table table;

    public Dealer() {
        super("Dealer");
        CardDao daoSource = new CardDaoXML();
        deck = new Deck(daoSource.getAllCards());
        table = new Table();
    }

    public Table getTable() {
        return table;
    }

    public void dealCards() {
        for (int i = 0; i < table.getPlayer().size(); i++) {
            dealFiveCards(i);
        }
    }

    private void dealFiveCards(int i) {
        Iterator<Card> deckIterator = deck.getDeckIterator();
        for (int j = 1; j < AMOUNT_OF_CARDS_PER_PLAYER; j++) {
            if (deckIterator.hasNext()) {
                Card card = deckIterator.next();
                table.getPlayer().get(i).getPile().addCard(card);
                card.setContainingPile(table.getPlayer().get(i).getPile());
            }
        }
    }
}
