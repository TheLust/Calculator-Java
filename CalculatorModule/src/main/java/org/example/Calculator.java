package org.example;

import java.util.Hashtable;
import java.util.Stack;

public class Calculator {

    Validator validator;

    public Calculator() {
        validator = new Validator();
    }
    public int calculate(String expression) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        boolean isNegativeNumber = false; //For negative numbers

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch >= '0' && ch <= '9') {
                StringBuilder num = new StringBuilder();

                //Adding - at the beginning of the number
                if (isNegativeNumber) {
                    num.append('-');
                    isNegativeNumber = false;
                }

                //Getting the full number
                while (i < expression.length() && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;

                numberStack.push(Integer.parseInt(num.toString()));

                if (i < expression.length() - 1 && expression.charAt(i + 1) == '(')
                    operatorStack.push('*');
            }
            else if (ch == '(') {
                operatorStack.push(ch);
            }
            else if (ch == ')') { //Bracket closed: do operations pushed till now then push the result back in the stack
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    int result = performOperation(numberStack, operatorStack);
                    numberStack.push(result);
                }

                if (!operatorStack.isEmpty() && operatorStack.peek() == '(')
                    operatorStack.pop();

                if (i < expression.length() - 1 && Character.isDigit(expression.charAt(i + 1)))
                    operatorStack.push('*');
            }
            else if (validator.isOperation(ch)) {
                if (i == 0  || expression.charAt(i - 1) == '(') {
                    //Checking if first char is sign or the prev char is bracket
                    isNegativeNumber = true;
                    continue;
                }

                while (!operatorStack.isEmpty() && hasPrecedence(ch, operatorStack.peek())) {
                    int result = performOperation(numberStack, operatorStack);
                    numberStack.push(result);
                }
                operatorStack.push(ch);
            }
        }

        while (!operatorStack.isEmpty()) {
            int result = performOperation(numberStack, operatorStack);
            numberStack.push(result);
        }

        return numberStack.pop();
    }

    //Method to handle the priority of operations
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private int operate(char operator, int x, int y) {
        if (operator == '/' && y == 0)
            throw new ArithmeticException("Cannot divide by 0.");

        Hashtable<Character, Integer> opHash = new Hashtable<>();
        opHash.put('+', x + y);
        opHash.put('-', x - y);
        opHash.put('*', x * y);
        opHash.put('/', x / y);

        return opHash.get(operator);
    }

    //Will do first operation in the operation stack
    private int performOperation(Stack<Integer> numberStack, Stack<Character> operatorStack) {
        int number2 = numberStack.pop();
        int number1 = numberStack.pop();
        char operator = operatorStack.pop();

        return operate(operator, number1, number2);
    }
}
