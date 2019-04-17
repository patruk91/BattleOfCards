package com.codecool.controller;

import com.codecool.comparator.CapacityComparator;
import com.codecool.comparator.PowerWeightComparator;
import com.codecool.comparator.PriceComparator;
import com.codecool.comparator.TopSpeedComparator;
import com.codecool.gameelement.*;
import com.codecool.reader.Reader;
import com.codecool.viewer.Viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        Pile destinationPile = null;
        switch (parameter) {
            case SPEED:
                Comparator<Card> speedComparator = new TopSpeedComparator();
                destinationPile = compareByComparator(speedComparator);
                break;
            case RATIO:
                Comparator<Card> ratioComparator = new PowerWeightComparator();
                destinationPile = compareByComparator(ratioComparator);
                break;
            case PRICE:
                Comparator<Card> priceComparator = new PriceComparator();
                destinationPile = compareByComparator(priceComparator);
                break;
            case CAPACITY:
                Comparator<Card> capacityComparator = new CapacityComparator();
                destinationPile = compareByComparator(capacityComparator);
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

    private Pile compareByComparator(Comparator comparator) {
        List<Card> cardsToCompare = getCardsToCompare();

        Collections.sort(cardsToCompare, comparator);
        if (comparator.compare(cardsToCompare.get(cardsToCompare.size() - 1), cardsToCompare.get(cardsToCompare.size() - 2)) == 0) {
            return table.getNotWonCards();
        }
        return cardsToCompare.get(cardsToCompare.size() - 1).getContainingPile();
    }

    private List<Card> getCardsToCompare() {
        List<Card> cardsToCompare = new ArrayList<>();

        for (Player player: table.getPlayer()) {
            cardsToCompare.add(player.getTopCardFromPile());
        }

        return cardsToCompare;
    }
}
