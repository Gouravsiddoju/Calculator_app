package com.example.temperatureapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Main_Activity extends AppCompatActivity {

    private EditText inputTemperature;
    private RadioGroup unitSelection;
    private Button convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTemperature = findViewById(R.id.inputTemperature);
        unitSelection = findViewById(R.id.unitSelection);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        double temperatureInput = Double.parseDouble(inputTemperature.getText().toString());
        int selectedRadioButtonId = unitSelection.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            resultText.setText("Select a temperature unit.");
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String selectedUnit = selectedRadioButton.getText().toString();

        double convertedTemperature;

        switch (selectedUnit) {
            case "Celsius":
                convertedTemperature = temperatureInput;
                break;
            case "Fahrenheit":
                convertedTemperature = (temperatureInput - 32) * 5 / 9;
                break;
            case "Kelvin":
                convertedTemperature = temperatureInput - 273.15;
                break;
            default:
                convertedTemperature = 0;
        }

        resultText.setText("Result: " + convertedTemperature + " " + selectedUnit);
    }
}
