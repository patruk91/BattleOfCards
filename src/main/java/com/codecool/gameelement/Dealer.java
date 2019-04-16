package com.codecool.gameelement;

import com.codecool.dao.CardDao;
import com.codecool.dao.CardDaoXML;

import java.util.Iterator;

public class Dealer {
    private static final int AMOUNT_OF_CARDS_PER_PLAYER = 5;
    private Deck deck;
    private Table table;
    private int playersAmount;

    public Dealer() {
        CardDao daoSource = new CardDaoXML();
        daoSource.getAllCards();
        Table table = new Table();
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
        Iterator deckIterator = deck.getIterator();
        for (int j = 1; j < AMOUNT_OF_CARDS_PER_PLAYER; j++) {
            if (deckIterator.hasNext()) {
                Card card = deckIterator.next();
                table.getPlayer().get(i).getPile().addCard(card);
                card.setCOntainingPile(table.getPlayer().get(i).getPile());
            }
        }
    }

    public void setPlayersAmount(int playerAmount) {
        this.playersAmount = playerAmount;
    }
}
