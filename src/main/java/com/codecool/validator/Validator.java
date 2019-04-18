package com.codecool.validator;

public class Validator {

    public boolean checkIfStringIsNotEmpty(String input) {
        return !input.isBlank();
    }

    public boolean checkIfNumber(String input) {
        return input.matches("[\\d]+");
    }

    public boolean checkIfNumberInRange(int start, int end, int input) {
        return (start <= input && input <= end);
    }

}
