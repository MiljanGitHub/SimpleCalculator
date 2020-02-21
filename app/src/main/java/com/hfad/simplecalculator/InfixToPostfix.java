package com.hfad.simplecalculator;

import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfix {

    //InfixToPostfix prima ArrayList<Object> koji su u infix formi, mi treba da ih prevedemo u postfix format
    //u kontejneru ArrayList<Object>

    /*
    Idea of InfixToPostfix class is to take infixed-formed list (of doubles and strings) and translate it to postfixed-formed list.
    Postfix notation, also known as Polish notation in CS, is one of three possible ways to write algebraic expressions.
    Postfix form is very well processed by machines. Meaning, they can very easily apply precedence rules
    using Stack data structure and evaluate expression.

     Example:

     Infix notation (how people usually write expressions): A+B-(D*E)

     Postfix notation: AB+DE*-
     */

    private ArrayList<Object> infixList;
    private ArrayList<Object> postfixList; //Result

    private  final String MINUS = "-";
    private  final String PLUS = "+";
    private  final String SLASH = "/";
    private  final String ASTERIKS = "*";
    private  final String POWER = "^";
    private  final String LEFT_PARANTHESIS = "(";
    private  final String RIGHT_PARANTHESIS = ")";


    public InfixToPostfix(ArrayList<Object> infixList) {

        this.infixList = infixList;
        postfixList = new ArrayList<Object>();

    }

    public ArrayList<Object> convertInfixToPostfix() throws Exception{
        Stack<Object> stack = new Stack<Object>();

        Object currentElement, topOfStack;


        for (Object o : infixList) {

            //currentElement = infixList.get(i);
            currentElement = o;
            if (currentElement instanceof Double) {
                postfixList.add(currentElement);

                // If the scanned character is an '(', push it to the stack.
            } else if (currentElement.equals(LEFT_PARANTHESIS)) {
                stack.push(currentElement);

                // If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            } else if (currentElement.equals(RIGHT_PARANTHESIS)) {

                while (!stack.isEmpty() && !stack.peek().equals(LEFT_PARANTHESIS)) {
                    topOfStack = stack.pop();
                    postfixList.add(topOfStack);
                }

                if (!stack.isEmpty() && !stack.peek().equals(LEFT_PARANTHESIS))
                    throw new Exception("Invalid Expression"); // invalid expression
                else
                    stack.pop();

                // an operator is encountered
            } else {

                //empty the stack:
                //1. while operator on the stack hash higher or equal precedence of the incoming operator,
                //2. and while stack is not empty
                while (!stack.isEmpty() && hasHigherOrEqualPrecedence((String)stack.peek(), (String) currentElement)){
                    if(stack.peek().equals(LEFT_PARANTHESIS))
                        throw new Exception("Invalid Expression"); // invalid expression
                    topOfStack = stack.pop();
                    postfixList.add(topOfStack);
                }
                stack.push(currentElement);
            }

        }
        while (!stack.isEmpty()){
            if(stack.peek().equals(LEFT_PARANTHESIS))
                throw new Exception("Invalid Expression"); // invalid expression
            topOfStack = stack.pop();
            postfixList.add(topOfStack);
        }


        return postfixList;
    } // od metode


    private  boolean hasHigherOrEqualPrecedence(String operatorFromStack, String operatorFromExpression) {

        int weightOperatorFromStack = getWeight(operatorFromStack);
        int weightOperatorFromExpression = getWeight(operatorFromExpression);

        return weightOperatorFromStack >= weightOperatorFromExpression;
    }

    private  int getWeight(String ch) {
        switch (ch) {
            case PLUS:
            case MINUS:
                return 1;
            case SLASH:
            case ASTERIKS:
                return 2;
            case POWER:
                return 3;
            default: return -1;
        }

    }
}
