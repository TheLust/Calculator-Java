package org.example;

public class Validator {
    public boolean isOperation (char op) {
        return switch (op) {
            case '+', '-', '*', '/' -> true;
            default -> false;
        };
    }

    public String format(String expression) {
        if (expression.contains("--"))
            return format(expression.replaceAll("--", "+"));

        if (expression.contains("++"))
            return format(expression.replaceAll("\\+\\+", "-"));

        if (expression.contains("+-"))
            return format(expression.replaceAll("\\+-", "-"));

        if (expression.contains("-+"))
            return format(expression.replaceAll("-\\+", "-"));

        if (expression.contains("**"))
            return format(expression.replaceAll("\\*\\*", "*"));

        if (expression.contains("//"))
            return format(expression.replaceAll("//", "/"));

        if (expression.contains("*+"))
            return format(expression.replaceAll("\\*\\+", "*"));

        if (expression.contains("+*"))
            return format(expression.replaceAll("\\+\\*", "*"));

        if (expression.contains("/-"))
            return format(expression.replaceAll("(\\d+)/(\\-(\\d+))", "$1/(-$3)"));

        if (expression.contains("*-"))
            return format(expression.replaceAll("(\\d+)\\*(-(\\d+))", "$1*(-$3)"));

        if (expression.charAt(0) == '+' || expression.charAt(0) == '*' || expression.charAt(0) == '/')
            return format(expression.substring(1));

        if (expression.charAt(expression.length() - 1) == '+' ||
                expression.charAt(expression.length() - 1) == '-' ||
                expression.charAt(expression.length() - 1) == '*' ||
                expression.charAt(expression.length() - 1) == '/')
            return format(expression.substring(0, expression.length() - 1));

        return expression;
    }

    public String validate(String expression) throws Exception {
        if (expression.isEmpty())
            throw new Exception("Empty string.");

        if ((isOperation(expression.charAt(0)) && expression.charAt(0) != '-') || isOperation(expression.charAt(expression.length() - 1)))
            throw new Exception("Detected invalid operation.");

        if (expression.chars().filter(c -> c == '(').count() != expression.chars().filter(c -> c == ')').count())
            throw new Exception("Invalid number of brackets.");

        if (expression.chars().filter(Character::isLetter).count() != 0)
            throw new Exception("Letters detected.");

        return expression;
    }
}
