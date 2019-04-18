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
    private Dealer dealer;
    private Table table;
    private Reader reader;
    private Viewer viewer;

    public GameLogic() {
        viewer = new Viewer();
        reader = new Reader(viewer);
        dealer = new Dealer();
        table = dealer.getTable();
    }

    public void startGame() {
        boolean exitApp = false;
        while(!exitApp) {
            viewer.printMenu();
            viewer.printQuestion("Chose option: ");
            int option = reader.getNumberInRange(0, 3);
            switch (option) {
                case 1:
                    playGame();
                    break;
                case 2:
                    viewer.rules();
                    break;
                case 3:
                    viewer.credits();
                    break;
                case 0:
                    exitApp = true;
                    break;
            }
        }
    }

    private void playGame() {
        int amountOfPlayers = getAmountOfPlayers();
        addPlayersToTable(amountOfPlayers);
        dealer.dealCards();
        setPlayerAsStarting(table.getPlayer().get(0), true);

        boolean isGameOver = false;
        while (!isGameOver) {
            Player startingPlayer = getStartingPlayer();
            showPlayersCardAmount();
            viewer.printMessage(startingPlayer.getName() + " your card:");
            viewer.printMessage(startingPlayer.getTopCardFromPile().toString());
            viewer.printMessage(startingPlayer.getName() + ": chose attribute to compare:");
            int parameter = getParameterToCompare();
            List<Card> cardsToCompare = getCardsToCompare();
            User destinationUser = getWiningUser(parameter, cardsToCompare);
            calculateAmountOfCards(destinationUser, cardsToCompare);
            viewer.printMessage(destinationUser.getName() + " won round");
            changeUsersOrder(destinationUser);
            checkIfUsersInGame();
            isGameOver = checkIfGameOver();
            if(isGameOver) {
                viewer.printWinScreen(table.getPlayer().get(0));
                resetGame();
            }
        }
    }

    private void showPlayersCardAmount() {
        for (Player player : table.getPlayer()) {
            System.out.println(player.getName() + " " + player.getPile().getCards().size());
        }
    }

    private void resetGame() {
        table.resetTable();
        dealer.resetIterator();
    }

    private User getWiningUser(int parameter, List<Card> cardsToCompare) {
        final int SPEED = 1;
        final int RATIO = 2;
        final int PRICE = 3;
        final int CAPACITY = 4;

        User destinationUser = dealer;
        switch (parameter) {
            case SPEED:
                destinationUser = compareByComparator(new TopSpeedComparator(), cardsToCompare);
                break;
            case RATIO:
                destinationUser = compareByComparator(new PowerWeightComparator(), cardsToCompare);
                break;
            case PRICE:
                destinationUser = compareByComparator(new PriceComparator(), cardsToCompare);
                break;
            case CAPACITY:
                destinationUser = compareByComparator(new CapacityComparator(), cardsToCompare);
        }
        return destinationUser;
    }

    private int getAmountOfPlayers() {
        viewer.printQuestion("How many players are going to play (2 - 5)");
        return reader.getNumberInRange(2, 5);
    }

    private void addPlayersToTable(int playersAmount) {
        for (int i = 0; i < playersAmount; i++) {
            String name = reader.getNameFromUser();
            table.addPlayer(name);
        }
    }

    private void setPlayerAsStarting(Player player, boolean bool) {
        player.setUserFirst(bool);
    }

    private int getParameterToCompare() {
        viewer.printAttributesToCompare();
        return reader.getNumberInRange(1, 4);
    }

    private Player getStartingPlayer() {
        for (Player player: table.getPlayer()) {
            if (player.isUserFirst()) {
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

    private void calculateAmountOfCards(User user, List<Card> cards) {
        System.out.println();
        if(!user.getName().equals("Dealer")) {
            moveCards(user, cards);
            if (!dealer.getPile().getCards().isEmpty()) {
                moveCards(user, dealer.getPile().getCards());
            }
        }
    }

    private void moveCards(User user, List<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            cards.get(i).moveToPile(user.getPile());
        }
    }


    private void changeUsersOrder(User winUser) {
        if(!winUser.getName().equals("Dealer")) {
            winUser.setUserFirst(true);
            for(User user: table.getPlayer()) {
                if(!user.getName().equals(winUser.getName())) {
                    user.setUserFirst(false);
                }
            }
        }
    }

    private void checkIfUsersInGame() {
        for(int i = 0; i < table.getPlayer().size(); i++) {
            Player player = table.getPlayer().get(i);
            if (player.getPile().getCards().isEmpty()) {
                viewer.printMessage(player.getName() + " los all his cards.");
                table.removePlayer(player);
            }
        }
    }

    private boolean checkIfGameOver() {
        return table.getPlayer().size() == 1;
    }
}
