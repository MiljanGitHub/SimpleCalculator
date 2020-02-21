package com.hfad.simplecalculator;

import java.util.ArrayList;

public class Converter {

    //ideja konvertera da prevede infix izraz u listu <Object> doubles i operatori.
    //Ovo ce takodje biti "infix" izraz

    /*
    Idea of a converter is to translate infix (string) expression into ArrayList<Object> of doubles and operators (strings).
    This list, we'll also be of a infix form.
     */

    private ArrayList<Object> result;
    private StringBuilder sb;
    private String infixExpression;


    public Converter(String infixExpression) {
        result  = new ArrayList<Object>();
        sb = new StringBuilder();
        this.infixExpression = infixExpression;

    }

    public ArrayList<Object> convert(){
        char currentChar;
        double tempDouble;

        for (int i= 0; i<infixExpression.length(); i++) {
            currentChar = infixExpression.charAt(i);

            if (isDigitOrDot(currentChar)) {
                sb.append(currentChar);
            } else { //operator
                if ( i != 0) {
                    if (Character.isDigit(infixExpression.charAt(i-1))) {
                        tempDouble = Double.valueOf(sb.toString());
                        result.add(tempDouble);
                        result.add(String.valueOf(currentChar));
                        sb.setLength(0);
                    } else result.add(String.valueOf(currentChar));
                } else result.add(String.valueOf(currentChar));


            }
        }

        if (sb.length() != 0)result.add(Double.valueOf(sb.toString()));
        //

        return result;
    }


    private  boolean isDigitOrDot(Character ch) {
        return Character.isDigit(ch) || ch == '.';
    }
}
