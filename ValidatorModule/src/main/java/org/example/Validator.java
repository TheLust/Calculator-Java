package org.example;

public class Validator {
    public boolean isOperation (char op) {
        return switch (op) {
            case '+', '-', '*', '/' -> true;
            default -> false;
        };
    }

    public void validate(String expression) throws Exception {
        if (expression.isEmpty())
            throw new Exception("Empty string.");

        if ((isOperation(expression.charAt(0)) && expression.charAt(0) != '-') || isOperation(expression.charAt(expression.length() - 1)))
            throw new Exception("Detected invalid operation.");

        if (expression.chars().filter(c -> c == '(').count() != expression.chars().filter(c -> c == ')').count())
            throw new Exception("Invalid number of brackets.");

        if (expression.chars().filter(Character::isLetter).count() != 0)
            throw new Exception("Letters detected.");

    }
}
