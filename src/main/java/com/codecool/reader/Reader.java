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

    public String getNameFromUser() {
        String result = "";
        boolean isAnswerCorrect = false;
        while(!isAnswerCorrect) {
            viewer.printQuestion("What is your name");
            String name = getStringFromUser();
            if(validator.checkIfStringIsNotEmpty(name)) {
                isAnswerCorrect = true;
                result = name;
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
                    if(validator.checkIfNumberInRange(intInput, start, end)) {
                        result = intInput;
                        isAnswerCorrect = true;
                    }
                }
            }
        }
        return result;
    }
}
