package com.codecool.gameelement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Player> players;

    public Table() {
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayer() {
        return players;
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name));
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void resetTable() {
        players.clear();
    }
}
