package com.example.ex3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.ICUUncheckedIOException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final static int ARITHMETIC_SEQUENCE = 0;
    final static int GEOMETRIC_SEQUENCE = 1;
    final static int NUM_ELEMENTS = 20;

    ListView elementsListView;
    TextView x1DataTextView;
    TextView dQTextView;
    TextView dqDataTextView;
    TextView nDataTextView;
    TextView snDataTextView;

    double firstElement;
    double difference;
    int sequenceType;
    String[] elements = new String[NUM_ELEMENTS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        elementsListView = findViewById(R.id.elementsListView);
        x1DataTextView = findViewById(R.id.x1DataTextView);
        dQTextView = findViewById(R.id.dQTextView);
        dqDataTextView = findViewById(R.id.dqDataTextView);
        nDataTextView = findViewById(R.id.nDataTextView);
        snDataTextView = findViewById(R.id.snDataTextView);

        Intent intent = getIntent();
        firstElement = intent.getDoubleExtra("firstElement", 1.0);
        difference = intent.getDoubleExtra("difference", 1.0);
        sequenceType = intent.getIntExtra("sequenceType", 1);

        elementsListView.setOnItemClickListener(this);
        setDataInLayout();
    }

    private void setDataInLayout() {
        x1DataTextView.setText(firstElement + "");

        if(sequenceType == ARITHMETIC_SEQUENCE) {
            dQTextView.setText("d = ");
        } else {
            dQTextView.setText("q = ");
        }

        dqDataTextView.setText(difference + "");

        double currentElement = firstElement;

        for(int i = 0; i < NUM_ELEMENTS; i++) {
            elements[i] = currentElement + "";

            if(sequenceType == ARITHMETIC_SEQUENCE) {
                currentElement = currentElement + difference;
            } else {
                currentElement = currentElement * difference;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, elements);
        elementsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        nDataTextView.setText(position + "");

        double sum = 0.0;

        for(int i = 0; i <= position; i++) {
            sum = sum + Double.parseDouble(elements[i]);
        }

        snDataTextView.setText(sum + "");
    }
}
