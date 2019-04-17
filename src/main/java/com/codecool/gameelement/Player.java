package com.codecool.gameelement;

public class Player extends User{
    private boolean isPlayerFirst = false;

    public Player(String playerName) {
        super(playerName);
    }

    public boolean isPlayerFirst() {
        return isPlayerFirst;
    }

    public void setPlayerFirst(boolean isPlayerFirst) {
        this.isPlayerFirst = isPlayerFirst;
    }

    public Card getTopCardFromPile() {
        return pile.getTopCard();
    }
}
