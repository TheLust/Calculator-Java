package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        String expression;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Calculator calculator = new Calculator();
        try {
            expression = reader.readLine();
            while (!expression.equals("exit")) {
                System.out.print(" = " + calculator.calculate(expression) + "\n");
                expression = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}