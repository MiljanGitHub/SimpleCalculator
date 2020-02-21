package com.hfad.simplecalculator;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StandardFragment extends Fragment implements View.OnClickListener{

    private TextView expression;
    private String savedState;
    //private double result;
    //ArrayList<View> allButtons;

    public StandardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null){
            savedState = savedInstanceState.getString("expression");
        }

        return inflater.inflate(R.layout.fragment_standard, container, false);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        //expression = getView().findViewById(R.id.expression);
        switch (v.getId()){
            case R.id.c_id:
                expression.setText("");
                break;
            case R.id.left_parenthesis_id:
                expression.append("(");
                break;
            case R.id.right_parenthesis_id:
                expression.append(")");
                break;
            case R.id.power_id:
                expression.append("^");
                break;
            case R.id.division_id:
                expression.append("/");
                break;
            case R.id.seven_id:
                expression.append("7");
                break;
            case R.id.eight_id:
                expression.append("8");
                break;
            case R.id.nine_id:
                expression.append("9");
                break;
            case R.id.multiply_id:
                expression.append("*");
                break;
            case R.id.four_id:
                expression.append("4");
                break;
            case R.id.five_id:
                expression.append("5");
                break;
            case  R.id.six_id:
                expression.append("6");
                break;
            case R.id.minus_id:
                expression.append("-");
                break;
            case R.id.one_id:
                expression.append("1");
                break;
            case R.id.two_id:
                expression.append("2");
                break;
            case R.id.three_id:
                expression.append("3");
                break;
            case R.id.plus_id:
                expression.append("+");
                break;
            case R.id.delete_id:
                if (expression.getText().length() > 0){
                    expression.setText(expression.getText().subSequence(0, expression.getText().length()-1));
                }
                break;
            case R.id.zero_id:
                expression.append("0");
                break;
            case R.id.point_id:
                expression.append(".");
                break;
            case R.id.equal_id:

                try{

                    Converter converter = new Converter(expression.getText().toString());
                    ArrayList<Object> infixList = converter.convert();

                    InfixToPostfix infixToPostfixObject = new InfixToPostfix(infixList);
                    ArrayList<Object> postfixList = infixToPostfixObject.convertInfixToPostfix();

                    EvaluatePostfix evaluatePostfixObject = new EvaluatePostfix(postfixList);
                    double result = evaluatePostfixObject.evaluate();

                    expression.setText(String.valueOf(result));


                } catch (Exception e){
                    Toast.makeText(getActivity(),"Invalid expression",Toast.LENGTH_SHORT).show();
                }


        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("expression", expression.getText().toString());
        //outState.putDouble("result", result);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        expression = getView().findViewById(R.id.expression);

        if (savedState != null){
            expression.setText(savedState);
        }

        Button c = (Button) getView().findViewById(R.id.c_id);
        c.setOnClickListener(this);

        Button left_parenthesis = (Button) getView().findViewById(R.id.left_parenthesis_id);
        left_parenthesis.setOnClickListener(this);

        Button right_parenthesis = (Button) getView().findViewById(R.id.right_parenthesis_id);
        right_parenthesis.setOnClickListener(this);

        Button power = (Button) getView().findViewById(R.id.power_id);
        power.setOnClickListener(this);

        Button division = (Button) getView().findViewById(R.id.division_id);
        division.setOnClickListener(this);

        Button seven = (Button) getView().findViewById(R.id.seven_id);
        seven.setOnClickListener(this);

        Button eight = (Button) getView().findViewById(R.id.eight_id);
        eight.setOnClickListener(this);

        Button nine = (Button) getView().findViewById(R.id.nine_id);
        nine.setOnClickListener(this);

        Button multiply = (Button) getView().findViewById(R.id.multiply_id);
        multiply.setOnClickListener(this);

        Button four = (Button) getView().findViewById(R.id.four_id);
        four.setOnClickListener(this);

        Button five = (Button) getView().findViewById(R.id.five_id);
        five.setOnClickListener(this);

        Button six = (Button) getView().findViewById(R.id.six_id);
        six.setOnClickListener(this);

        Button minus = (Button) getView().findViewById(R.id.minus_id);
        minus.setOnClickListener(this);

        Button one = (Button) getView().findViewById(R.id.one_id);
        one.setOnClickListener(this);

        Button two = (Button) getView().findViewById(R.id.two_id);
        two.setOnClickListener(this);

        Button three = (Button) getView().findViewById(R.id.three_id);
        three.setOnClickListener(this);

        Button plus = (Button) getView().findViewById(R.id.plus_id);
        plus.setOnClickListener(this);

        Button erase = (Button) getView().findViewById(R.id.delete_id);
        erase.setOnClickListener(this);

        Button zero = (Button) getView().findViewById(R.id.zero_id);
        zero.setOnClickListener(this);

        Button point = (Button) getView().findViewById(R.id.point_id);
        point.setOnClickListener(this);

        Button equal = (Button) getView().findViewById(R.id.equal_id);
        equal.setOnClickListener(this);
    }

}
