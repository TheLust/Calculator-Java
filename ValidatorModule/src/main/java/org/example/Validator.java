package org.example;

public class Validator {
    public boolean isOperation (char op) {
        return switch (op) {
            case '+', '-', '*', '/' -> true;
            default -> false;
        };
    }

    public String format(String expression) {
        return expression.replaceAll("\\+-", "-")
                         .replaceAll("--", "+")
                         .replaceAll("(\\d+|\\))/-?(\\d+)", "$1/(-$2)")
                         .replaceAll("(\\d+|\\))\\*-?(\\d+)", "$1*(-$2)");
    }

    //
    public void validate(String expression) throws Exception {
        if (expression.isBlank() || expression.isEmpty())
            throw new Exception("Empty string.");

        if ((isOperation(expression.charAt(0)) && expression.charAt(0) != '-') || isOperation(expression.charAt(expression.length() - 1)))
            throw new Exception("Detected invalid operation.");

        long openBr = expression.chars().filter(c -> c == '(').count();
        long closedBr = expression.chars().filter(c -> c == ')').count();

        if (openBr > closedBr)
            throw new Exception("Invalid number of brackets. You need to close " + (openBr - closedBr) + " brackets.");

        if (openBr < closedBr)
            throw new Exception("Invalid number of brackets. You need to open " + (closedBr - openBr) + " brackets.");

        if (!expression.matches("^[0-9+\\-*/()]+$"))
            throw new Exception("Character detected.");

        if (expression.matches("\\(.*[+*/].*"))
            throw new Exception("Invalid expression (.");

        if (expression.matches("[-+*/].*\\)"))
            throw new Exception("Invalid expression ).");

    }
}
