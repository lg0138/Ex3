package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Switch sequenceTypeSwitch;
    EditText firstElementEditText;
    EditText differenceEditText;
    Button displayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sequenceTypeSwitch = findViewById(R.id.sequenceTypeSwitch);
        firstElementEditText = findViewById(R.id.firstElementEditText);
        differenceEditText = findViewById(R.id.differenceEditText);
        displayButton = findViewById(R.id.displayButton);
        displayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(firstElementEditText.getText().toString().length() == 0
            || differenceEditText.getText().toString().length() == 0) {
            Toast.makeText(this,
                    "Please Enter first element and difference",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        boolean switchValue = sequenceTypeSwitch.isChecked();
        int sequenceType;

        if(switchValue) {
            sequenceType = DisplayActivity.GEOMETRIC_SEQUENCE;
        } else {
            sequenceType = DisplayActivity.ARITHMETIC_SEQUENCE;
        }

        double firstElement
                = Double.parseDouble(firstElementEditText.getText().toString());
        double difference
                = Double.parseDouble(differenceEditText.getText().toString());

        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("sequenceType", sequenceType);
        intent.putExtra("firstElement", firstElement);
        intent.putExtra("difference", difference);
        startActivity(intent);
    }
}
