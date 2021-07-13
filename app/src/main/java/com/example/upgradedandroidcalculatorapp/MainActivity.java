package com.example.upgradedandroidcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDivision, btnMultiply, btnSubtract, btnAdd, btnNegative, btnDecimal, btnClear, btnEquals;
    private double num, result;
    private char operator;
    private TextView calculatorDisplay;
    private boolean firstCalculation, decimalCheck;
    private Object lastButton;
    private String displayContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up display window
        calculatorDisplay = findViewById(R.id.calculatorDisplay);

        // Setting boolean flags
        firstCalculation = true;
        decimalCheck = false;

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnAdd = findViewById(R.id.btnAdd);
        btnNegative = findViewById(R.id.btnNegative);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnClear = findViewById(R.id.btnClear);
        btnEquals = findViewById(R.id.btnEquals);

        btn0.setOnClickListener(this::onClick);


//        lastButton
    }

    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn0:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "0");
                decimalCheck(0);
                break;
            case R.id.btn1:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "1");
                decimalCheck(1);
                break;
            case R.id.btn2:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "2");
                decimalCheck(2);
                break;
            case R.id.btn3:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "3");
                decimalCheck(3);
                break;
            case R.id.btn4:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "4");
                decimalCheck(4);
                break;
            case R.id.btn5:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "5");
                decimalCheck(5);
                break;
            case R.id.btn6:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "6");
                decimalCheck(6);
                break;
            case R.id.btn7:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "7");
                decimalCheck(7);
                break;
            case R.id.btn8:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "8");
                decimalCheck(8);
                break;
            case R.id.btn9:
                clearDisplayedResult();
                calculatorDisplay.setText(calculatorDisplay.getText() + "9");
                decimalCheck(9);
                break;
            case R.id.btnEquals:
                calculate();
                num = result;
                firstCalculation = true;
                decimalCheck = false;
                break;
            case R.id.btnAdd:
                calculatorDisplay.setText(""); // Clear the text field
                operator = '+';
                storeNum();
                break;
            case R.id.btnSubtract:
                calculatorDisplay.setText(""); // Clear the text field
                operator = '-';
                storeNum();
                break;
            case R.id.btnMultiply:
                calculatorDisplay.setText("");
                operator = '*';
                storeNum();
                break;
            case R.id.btnDivision:
                calculatorDisplay.setText("");
                operator = '/';
                storeNum();
                break;
            case R.id.btnClear:
                calculatorDisplay.setText("");
                num = 0;
                result = 0;
                firstCalculation = true;
                decimalCheck = false;
                break;
            case R.id.btnDecimal:
                int temp = (int) num;
                String tempString = String.valueOf(temp) + ".";
                num = Double.parseDouble(tempString);
                calculatorDisplay.setText(tempString);
                decimalCheck = true;
                break;
            case R.id.btnNegative:
                num = num * -1;
                calculatorDisplay.setText(String.valueOf(num));
                break;
        }

    }

    /**
     * Performs the mathematical operations for the calculator.
     */
    public void calculate() {

        switch (operator) {
            case '+':
                result = result + num;
                num = 0; // Reset num
                break;
            case '-':
                result = result - num;
                num = 0; // Reset num
                break;
            case '*':
                result = result * num;
                num = 0; // Reset num
                break;
            case '/':
                if (num == 0) { // Check for division by zero. If so, display error message.
//                    textArea.setText("DivZero");
                    num = 0;
                    result = 0;
                    return;
                }
                result = result / num;
                num = 0; // Reset num
                break;
            default:
                /* Triggered if no operator has been pressed but the equals button has
                 * to prevent the current num and textField from being reset to zero in
                 * this event. */
                result = num;
                break;
        }
        roundResult();
//        textArea.setText(String.valueOf(result));
    }

    /**
     * Checks if the calculator is currently displaying the result of a previous calculation.
     * If true, it clears the text area. Otherwise, it does nothing.
     */
    public void clearDisplayedResult() {

        if (calculatorDisplay.getText().equals("") && Double.parseDouble((String) calculatorDisplay.getText()) == result) {
            // Clear display
            calculatorDisplay.setText("");
        }

    }

    /**
     * Stores the num variable in the result variable if on the first calculation,
     * or calls the calculate() method if a calculation needs to be performed.
     */
    public void storeNum() {
        if (firstCalculation) {
            result = num;
            num = 0;
            firstCalculation = false;
        } else {
            calculate();
        }
    }

    /**
     * Properly converts the current input to a decimal value so that
     * it can be used for calculations.
     */
    public void decimalCheck(int value) {
        if (decimalCheck) {
            int temp = (int) num;
            String stringTemp = String.valueOf(temp) + "." + value;
            num = Double.parseDouble(stringTemp);
//            textArea.setText(stringTemp);
            decimalCheck = false;
        } else {
            num = num * 10 + value;
        }
    }

    /**
     * Rounds the result variable to two decimal places.
     */
    public void roundResult() {
        result = (double) Math.round(result * 100) / 100;
    }

}