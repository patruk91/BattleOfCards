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

        int amountOfPlayers = getAmountOfPlayers();
        addPlayersToTable(amountOfPlayers);
        setPlayerAsStarting(table.getPlayer().get(0), true);
        Player startingPlayer = getStartingPlayer();
        viewer.printMessage(startingPlayer.getName() + ": chose attribute to compare:");
        int parameter = getParameterToCompare();
        List<Card> cardsToCompare = getCardsToCompare();
        User destinationUser = dealer;
        switch (parameter) {
            case SPEED:
                Comparator<Card> speedComparator = new TopSpeedComparator();
                destinationUser = compareByComparator(speedComparator, cardsToCompare);
                break;
            case RATIO:
                Comparator<Card> ratioComparator = new PowerWeightComparator();
                destinationUser = compareByComparator(ratioComparator, cardsToCompare);
                break;
            case PRICE:
                Comparator<Card> priceComparator = new PriceComparator();
                destinationUser = compareByComparator(priceComparator, cardsToCompare);
                break;
            case CAPACITY:
                Comparator<Card> capacityComparator = new CapacityComparator();
                destinationUser = compareByComparator(capacityComparator, cardsToCompare);
        }
        calculateAmountOfCards(destinationUser, cardsToCompare, amountOfPlayers);
        viewer.printMessage(destinationUser.getName() + " won round");

    }

    private int getAmountOfPlayers() {
        viewer.printQuestion("How many players are going to play (2 - 5)");
        int playersAmount = reader.getNumberInRange(2, 5);
        return playersAmount;
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

    private User compareByComparator(Comparator comparator, List<Card> cards) {
        Collections.sort(cards, comparator);
        if (comparator.compare(cards.get(cards.size() - 1), cards.get(cards.size() - 2)) == 0) {
            return dealer;
        }
        return cards.get(cards.size() - 1).getContainingPile().getContainingUser();
    }

    private List<Card> getCardsToCompare() {
        List<Card> cardsToCompare = new ArrayList<>();

        for (Player player: table.getPlayer()) {
            cardsToCompare.add(player.getTopCardFromPile());
        }

        return cardsToCompare;
    }

    private void calculateAmountOfCards(User user, List<Card> cards, int amountOfPlayers) {
        int cardsToMove = 0;
        if(user.getName().equals("Dealer")) {
            cardsToMove = amountOfPlayers;
        } else {
            cardsToMove = amountOfPlayers - 1;
        }
        moveCards(user, cards, cardsToMove);
        if(!user.getName().equals("Dealer") && !dealer.getPile().getCards().isEmpty()) {
            List<Card> dealerCards = dealer.getPile().getCards();
            moveCards(user, dealerCards, dealerCards.size());
        }
    }

    private void moveCards(User user, List<Card> cards, int amountOfCards) {
        for(int i = 0; i < amountOfCards; i++) {
            cards.get(i).moveToPile(user.getPile());
        }
    }
}
