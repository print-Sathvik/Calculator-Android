package com.example.calculatornew;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udojava.evalex.Expression;

import java.util.HashMap;

public class CalculateActivity extends AppCompatActivity {
    String query = "", result="";
    TextView queryTv, resultTv;
    HashMap<String, String> keySymbols = new HashMap<String, String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        queryTv = findViewById(R.id.query);
        resultTv = findViewById(R.id.result);
        keySymbols.put("X", "*");
        keySymbols.put("÷","/");
        keySymbols.put("√X","sqrt(");
        keySymbols.put("1/x","^(-1)");
        keySymbols.put("π","pi");
        keySymbols.put("ln","log(");
        keySymbols.put("lg","2.3026*log(");
        keySymbols.put("sin","sin(");
        keySymbols.put("cos","cos(");
        keySymbols.put("tan","tan(");
        keySymbols.put("X!","!");
    }

    public void calculate(View v)
    {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        if(buttonText.equals("ac")){
            query = "";
            result = "";
            queryTv.setText("0");
            resultTv.setText("0");
            return;
        }
        else if(keySymbols.containsKey(buttonText))
        {
            query = query + keySymbols.get(buttonText);
        }
        else
        {
            query = query + b.getText().toString();
        }

        queryTv.setText(query);
    }

    public void backButton(View v)
    {
        if(query.length() > 0) {
            query = query.substring(0, query.length() - 1);
        }
        queryTv.setText(query);
    }

    public void result(View v)
    {
        Button b = (Button)v;
        result = evaluate(query);
        resultTv.setText(result);
    }

    String evaluate(String query)
    {
        try{
            Expression expression=new Expression(query); //This Library Evaluate It
            String result = expression.eval().toString();
            return "="+result;
        } catch(Exception e) {
            return "Error";
        }
    }
}
