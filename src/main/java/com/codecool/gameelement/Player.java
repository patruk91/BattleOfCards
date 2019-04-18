package com.codecool.gameelement;

public class Player extends User{


    public Player(String playerName) {
        super(playerName);
    }

    public Card getTopCardFromPile() {
        return getPile().getTopCard();
    }
}
