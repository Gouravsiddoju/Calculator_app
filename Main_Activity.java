package com.example.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {
    private TextView resultTextView;
    private double operand1 = 0.0;
    private String operator = null;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Initialize buttons and set click listeners
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button[] buttons = {
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9),
                findViewById(R.id.buttonPlus),
                findViewById(R.id.buttonMinus),
                findViewById(R.id.buttonMultiply),
                findViewById(R.id.buttonDivide),
                findViewById(R.id.buttonclear),
                findViewById(R.id.buttonEqual),

        };

        for (Button button : buttons) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(button);
            }
        });
    }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.matches("[0-9]")) {
            // Handle number buttons (0-9)
            if (isOperatorClicked) {
                // If an operator was clicked previously, reset the resultTextView
                resultTextView.setText(buttonText);
                isOperatorClicked = false;
            } else {
                // Append the digit to the current number
                resultTextView.append(buttonText);
            }
        } else if (buttonText.matches("[\\+\\-\\*/]")) {
            // Handle operator buttons (+, -, *, /)
            if (!isOperatorClicked) {
                // If an operator was not clicked previously, perform calculation
                if (operand1 == 0.0) {
                    // If it's the first operand, set it to the current number
                    operand1 = Double.parseDouble(resultTextView.getText().toString());
                } else {
                    // If it's the second operand, perform the previous calculation
                    double operand2 = Double.parseDouble(resultTextView.getText().toString());
                    switch (operator) {
                        case "+":
                        operand1 += operand2;
                        break;
                        case "-":
                        operand1 -= operand2;
                        break;
                        case "*":
                        operand1 *= operand2;
                        break;
                        case "/":
                        if (operand2 != 0.0) {
                            operand1 /= operand2;
                        } else {
                            // Handle division by zero
                            resultTextView.setText("Error");
                            return;
                        }
                        break;
                    }
                }

                // Display the result and update the operator
                resultTextView.setText(String.valueOf(operand1));
                operator = buttonText;
                isOperatorClicked = true;
            }
        } else if (buttonText.equals("=")) {
            // Handle the equals button
            if (!isOperatorClicked) {
                double operand2 = Double.parseDouble(resultTextView.getText().toString());
                switch (operator) {
                    case "+":
                    operand1 += operand2;
                    break;
                    case "-":
                    operand1 -= operand2;
                    break;
                    case "*":
                    operand1 *= operand2;
                    break;
                    case "/":
                    if (operand2 != 0.0) {
                        operand1 /= operand2;
                    } else {
                        // Handle division by zero
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
                }

                // Display the final result
                resultTextView.setText(String.valueOf(operand1));
                operator = null;
                isOperatorClicked = true;
            }
        } else if (buttonText.equals("C")) {
            // Handle the clear (C) button to reset the calculator
            resultTextView.setText("0");
            operand1 = 0.0;
            operator = null;
            isOperatorClicked = false;
        }
    }
}
