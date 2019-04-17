package com.codecool.gameelement;

public class Player {
    private String playerName;
    private Pile pile;
    private boolean isPlayerFirst = false;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Pile getPile() {
        return pile;
    }

    public boolean isPlayerFirst() {
        return isPlayerFirst;
    }

    public void setPlayerFirst(boolean isPlayerFirst) {
        this.isPlayerFirst = isPlayerFirst;
    }
}
