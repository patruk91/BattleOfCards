package com.codecool.reader;

import com.codecool.validator.Validator;
import com.codecool.viewer.Viewer;

import java.util.Scanner;

public class Reader {
    Scanner scanner;
    Validator validator;
    Viewer viewer;

    public Reader(Viewer viewer) {
        scanner = new Scanner(System.in);
        validator = new Validator();
        this.viewer = viewer;
    }

    private String getStringFromUser() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public String getNameFromUser(int i) {
        String result = "";
        boolean isAnswerCorrect = false;
        while(!isAnswerCorrect) {
            viewer.printQuestion("Player" + i + " what is your name");
            String name = getStringFromUser();
            if(validator.checkIfStringIsNotEmpty(name)) {
                isAnswerCorrect = true;
                result = name;
            }
        }
        return result;
    }

    public String getCarName() {
        String result = "";
        boolean isAnswerCorrect = false;
        while(!isAnswerCorrect) {
            viewer.printQuestion("Enter car name");
            String name = getStringFromUser();
            if(validator.checkIfStringIsNotEmpty(name)) {
                isAnswerCorrect = true;
                result = name;
            } else {
                viewer.printError();
            }
        }
        return result;
    }

    public int getNumberInRange(int start, int end) {
        int result = 0;
        boolean isAnswerCorrect = false;
        while(!isAnswerCorrect) {
            String input = getStringFromUser();
            if(validator.checkIfStringIsNotEmpty(input)) {
                if(validator.checkIfNumber(input)) {
                    int intInput = Integer.parseInt(input);
                    if(validator.checkIfNumberInRange(start, end, intInput)) {
                        result = intInput;
                        isAnswerCorrect = true;
                    }
                }
            }
        }
        return result;
    }

    public double getStatisticNumber(String question) {
        double result = 0;
        boolean isAnswerCorrect = false;
        while(!isAnswerCorrect) {
            viewer.printQuestion(question);
            String input = getStringFromUser();
            if(validator.checkIfStringIsNotEmpty(input)) {
                if(validator.checkIfNumber(input)) {
                    double intInput = Double.parseDouble(input);
                    if(intInput >= 0) {
                        result = intInput;
                        isAnswerCorrect = true;
                    }
                } else {
                    viewer.printError();
                }
            } else {
                viewer.printError();
            }
        }
        return result;
    }
}
