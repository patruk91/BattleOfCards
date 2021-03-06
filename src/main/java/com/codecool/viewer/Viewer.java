package com.codecool.viewer;
import com.codecool.gameelement.Player;

public class Viewer {

    public Viewer() {

    }

    public void printMenu() {
        System.out.println("Cars card game v1.0");
        System.out.println("1. Game start\n2. Edit cards\n3. Rules\n4. Credits\n0. Exit");
    }

    public void printXmlEditorMenu() {
        System.out.println("1. Add car\n2. Edit car\n3. Remove car\n0. Exit");
    }

    public void printAttributesToCompare() {
        System.out.println("1. Top Speed\n2. Power / Weight ratio\n3. Price\n4. Capacity ");
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
        System.out.print(question + "?: ");
    }

    public void rules() {
        System.out.println("Rules");
    }

    public void credits() {
        System.out.println("Credits");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
