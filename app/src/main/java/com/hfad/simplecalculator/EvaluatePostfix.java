package com.hfad.simplecalculator;

import java.util.ArrayList;
import java.util.Stack;

public class EvaluatePostfix {

    /*
    A class to evaluate postfix expression to get a result.
     */

    private ArrayList<Object> postfixList;


    public EvaluatePostfix(ArrayList<Object> postfixList) {
        this.postfixList = postfixList;
    }

    public double evaluate() {
        // Method to evaluate value of a postfix expression

        //create a stack
        Stack<Object> stack=new Stack<Object>();

        // Scan all Oobjects one by one
        for(Object o : postfixList)
        {


            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(o instanceof Double)
                stack.push(o);

            // If the scanned character is an operator, pop two// elements from stack apply the operator
            else
            {
                double val1 = (double) stack.pop();
                double val2 = (double) stack.pop();

                switch(o.toString())
                {
                    case "+":
                        stack.push(val2+val1);
                        break;

                    case "-":
                        stack.push(val2- val1);
                        break;

                    case "/":
                        stack.push(val2/val1);
                        break;

                    case "*":
                        stack.push(val2*val1);
                        break;

                    case "^":
                        stack.push(Math.pow(val2, val1));
                        break;
                }
            }
        }
        return (double) stack.pop();
    }
}
