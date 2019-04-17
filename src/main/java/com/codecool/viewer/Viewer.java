package com.codecool.viewer;
import com.codecool.gameelement.Player;

public class Viewer {

    public Viewer() {

    }

    public void printMenu() {
        System.out.println("Cars card game v1.0");
        System.out.println("1. Game start\n 2. Rules\n 3. Credits 0. Exit");
    }

    public void printAttributesToCompare() {
        System.out.println("Comparing top cards: ");
        
    }

    public void printError(String string) {
        System.out.println("");
    }

    public void printMessage(String string) {
        System.out.println("");
    }

    public void printWinScreen(Player player) {
        System.out.println("");
    }

    public void printQuestion() {
        System.out.println("");
    }
}
