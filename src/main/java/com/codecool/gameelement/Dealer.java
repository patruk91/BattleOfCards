package com.codecool.gameelement;

import com.codecool.dao.CardDao;
import com.codecool.dao.CardDaoXML;
import com.codecool.iteratorinterface.ResetIterator;

import java.util.Collections;
import java.util.Iterator;

public class Dealer extends User{
    private static final int AMOUNT_OF_CARDS_PER_PLAYER = 5;
    private Deck deck;
    private Table table;
    private ResetIterator<Card> deckIterator;

    public Dealer() {
        super("Dealer");
        CardDao daoSource = new CardDaoXML();
        deck = new Deck(daoSource.getAllCards());
        deckIterator = deck.getDeckIterator();
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
        for (int j = 0; j < AMOUNT_OF_CARDS_PER_PLAYER; j++) {
            if (deckIterator.hasNext()) {
                Card card = deckIterator.next();
                table.getPlayer().get(i).getPile().addCard(card);
                card.setContainingPile(table.getPlayer().get(i).getPile());
            }
        }
    }

    public void resetIterator() {
        deckIterator.resetIterator();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck.getDeck());
    }

}
