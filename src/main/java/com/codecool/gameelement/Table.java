package com.codecool.gameelement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Player> players;
    private Pile nonWonCards;
    private List<Card> cardsToCompare = new ArrayList<>();

    public Table() {
        this.players = new ArrayList<Player>();
        this.nonWonCards = new Pile();
    }

    public List<Player> getPlayer() {
        return players;
    }

    public Pile getNotWonCards() {
        return nonWonCards;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name));
    }

    public void addCardsToCompare(Player player) {
        this.cardsToCompare.add(player.getTopCardFromPile());
    }

    public void addToNonWonCards(Card nonWonCard) {
        this.nonWonCards.addCard(nonWonCard);
    }
}
