package com.codecool.gameelement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Player> players;
    private List<Card> cardsToCompare = new ArrayList<>();

    public Table() {
        this.players = new ArrayList<Player>();
    }

    public List<Player> getPlayer() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name));
    }

    public void addCardsToCompare(Player player) {
        this.cardsToCompare.add(player.getTopCardFromPile());
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
