package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        String expression = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Calculator calculator = new Calculator();
        Validator validator = new Validator();

        do {
            try {
                expression = reader.readLine();
                expression = validator.format(expression);
                expression = validator.validate(expression);
                System.out.print(" = " + calculator.calculate(expression) + "\n");
            }
            catch (Exception e) {
                System.out.println(" = " + e.getMessage());
            }
        } while (!expression.equals("0"));
    }

//    public static void main(String[] args) {
//        String expression = "-----2--3+**3/-12";
//        Validator validator = new Validator();
//        System.out.println(validator.format(expression));
//        expression = validator.format(expression);
//        System.out.println(expression.length());
//    }
}