package com.codecool.controller;

import com.codecool.comparator.CapacityComparator;
import com.codecool.comparator.PowerWeightComparator;
import com.codecool.comparator.PriceComparator;
import com.codecool.comparator.TopSpeedComparator;
import com.codecool.gameelement.*;
import com.codecool.reader.Reader;
import com.codecool.viewer.Viewer;

import java.util.*;

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
            viewer.clearScreen();
            viewer.printMenu();
            viewer.printQuestion("Chose option");
            int option = reader.getNumberInRange(0, 3);
            switch (option) {
                case 1:
                    playGame();
                    break;
                case 2:
                    EditorController editor = new EditorController(reader, viewer);
                    break;
                case 3:
                    viewer.rules();
                    break;
                case 4:
                    viewer.credits();
                    break;
                case 0:
                    exitApp = true;
                    break;
            }
        }
    }

    private void playGame() {
        viewer.clearScreen();
        int amountOfPlayers = getAmountOfPlayers();
        viewer.clearScreen();
        addPlayersToTable(amountOfPlayers);
        dealer.shuffleDeck();
        dealer.dealCards();
        setPlayerAsStarting(table.getPlayer().get(0), true);

        boolean isGameOver = false;
        while (!isGameOver) {
            viewer.clearScreen();
            Player startingPlayer = getStartingPlayer();
            showPlayersCardAmount();
            viewer.printMessage(startingPlayer.getName() + " your card:\n");
            viewer.printMessage(startingPlayer.getTopCardFromPile().toString() + "\n");
            int parameter = getParameterToCompare(startingPlayer);
            List<Card> cardsToCompare = getCardsToCompare();
            User destinationUser = getWiningUser(parameter, cardsToCompare);
            calculateAmountOfCards(destinationUser, cardsToCompare);
            viewer.printMessage(destinationUser.getName() + " won round");
            changeUsersOrder(destinationUser);
            checkIfUsersInGame();
            isGameOver = checkIfGameOver();
            promptEnterKey();
            if(isGameOver) {
                viewer.printWinScreen(table.getPlayer().get(0));
                promptEnterKey();
                resetGame();
            }
        }
    }

    private void showPlayersCardAmount() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : table.getPlayer()) {
            stringBuilder.append(String.format("%s: %s cards\t", player.getName(), player.getPile().getCards().size()));
        }
        stringBuilder.append("\n");
        viewer.printMessage(stringBuilder.toString());
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
            String name = reader.getNameFromUser(i + 1);
            table.addPlayer(name);
        }
    }

    private void setPlayerAsStarting(Player player, boolean bool) {
        player.setUserFirst(bool);
    }

    private int getParameterToCompare(Player player) {
        viewer.printAttributesToCompare();
        viewer.printQuestion(player.getName() + ": chose attribute to compare");
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
        if (!user.getName().equals("Dealer")) {
            moveCards(user, cards);
            if (!dealer.getPile().getCards().isEmpty()) {
                moveCardsFromDealer(user, dealer.getPile().getCards());
            }
        } else {
            moveCards(dealer, cards);
        }
    }

    private void moveCards(User user, List<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            cards.get(i).moveToPile(user.getPile());
        }
    }

    private void moveCardsFromDealer(User user, List<Card> cards) {
        for(int i = 0; i < cards.size(); i++) {
            cards.get(i).moveFromDealer(user.getPile());
        }
        dealer.getPile().getCards().clear();
    }


    private void changeUsersOrder(User winUser) {
        if(!winUser.getName().equals("Dealer")) {
            winUser.setUserFirst(true);
            changeOtherUsersOrder(winUser);
        }
    }

    private void changeOtherUsersOrder(User winUser) {
        for(User user: table.getPlayer()) {
            if(!user.getName().equals(winUser.getName())) {
                user.setUserFirst(false);
            }
        }
    }

    private void checkIfUsersInGame() {
        Iterator<Player> playerIterator = table.getPlayer().iterator();
        while(playerIterator.hasNext()) {
            Player player = playerIterator.next();
            if(player.getPile().getCards().isEmpty()) {
                viewer.printMessage(player.getName() + " los all his cards.");
                playerIterator.remove();
            }
        }
    }

    private boolean checkIfGameOver() {
        return table.getPlayer().size() == 1;
    }

    public void promptEnterKey(){
        viewer.printMessage("Press 'ENTER' to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
