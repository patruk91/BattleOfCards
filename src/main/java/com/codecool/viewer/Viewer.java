package com.codecool.viewer;
import com.codecool.gameelement.Player;
import com.codecool.gameelement.Card;

public class Viewer {

    public Viewer() {

    }

    public void printMenu() {
        System.out.println("Cars card game v1.0");
        System.out.println(" 1. Game start\n 2. Rules\n 3. Credits\n 0. Exit");
    }

    public void printAttributesToCompare() {
        System.out.println("1. Top Speed\n2. Power / Weight ratio\n 3. Price\n 4. Capacity ");
    }

    public void printError() {
        System.out.println("Enter valid data");
    }

    public void printMessage(String string) {
        System.out.println(string);
    }

    public void printWinScreen(Player player) {
        System.out.println("Congratulations! " + player.getName());
        System.out.println("You won!");
    }

    public void printQuestion(String question) {
        System.out.println(question);
    }
}
