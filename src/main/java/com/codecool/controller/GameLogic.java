package com.codecool.controller;

import com.codecool.gameelement.Dealer;
import com.codecool.gameelement.Player;
import com.codecool.gameelement.Table;
import com.codecool.reader.Reader;
import com.codecool.viewer.Viewer;

public class GameLogic {
    Dealer dealer;
    Table table;
    Reader reader;
    Viewer viewer;

    public GameLogic() {
        viewer = new Viewer();
        reader = new Reader(viewer);
        dealer = new Dealer();
        table = dealer.getTable();
    }

    public void startGame() {
        final int SPEED = 1;
        final int RATIO = 2;
        final int PRICE = 3;
        final int CAPACITY = 4;

        getAmountOfPlayers();
        setPlayerAsStarting(table.getPlayer().get(0), true);
        Player startingPlayer = getStartingPlayer();
        viewer.printMessage(startingPlayer.getPlayerName() + ": chose attribute to compare:");
        int parameter = getParameterToCompare();
        switch (parameter) {
            case SPEED:
                compareBySpped();
                break;
            case RATIO:
                compareByRation();
                break;
            case PRICE:
                compareByPrice();
                break;
            case CAPACITY:
                compareByCapacity();
        }
    }

    private void getAmountOfPlayers() {
        viewer.printQuestion("How many players are going to play (2 - 5)");
        int playersAmount = reader.getNumberInRange(2, 5);
        addPlayersToTable(playersAmount);
    }

    private void addPlayersToTable(int playersAmount) {
        for (int i = 0; i < playersAmount; i++) {
            String name = reader.getNameFromUser();
            table.addPlayer(name);
        }
    }

    private void setPlayerAsStarting(Player player, boolean bool) {
        player.setPlayerFirst(bool);
    }

    private int getParameterToCompare() {
        viewer.printAttributesToCompare();
        int attribute = reader.getNumberInRange(1, 4);
        return attribute;
    }

    private Player getStartingPlayer() {
        for (Player player: table.getPlayer()) {
            if (player.isPlayerFirst()) {
                return player;
            }
        }
        return new Player("NoOne");
    }

    private Player compareBySpped() {

    }

    private Player compareByRation() {

    }

    private Player compareByPrice() {

    }

    private Player compareByCapacity() {
        
    }
}
