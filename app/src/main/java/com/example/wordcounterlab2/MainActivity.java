package com.example.wordcounterlab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtInput;
    private Spinner spinnerChoice;
    private Button btnCount;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = findViewById(R.id.txtInput);
        spinnerChoice = findViewById(R.id.spinnerChoice);
        btnCount = findViewById(R.id.btnCount);
        txtResult = findViewById(R.id.txtResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.metrics_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChoice.setAdapter(adapter);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = txtInput.getText().toString();
                if (inputText.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.toast_empty_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Use selected position to avoid string matching/localization issues
                int selected = spinnerChoice.getSelectedItemPosition();
                int result = 0;
                switch (selected) {
                    case 0: // Sentences
                        result = TextMetrics.countSentences(inputText);
                        break;
                    case 1: // Words
                        result = TextMetrics.countWords(inputText);
                        break;
                    case 2: // Characters
                        result = TextMetrics.countChars(inputText);
                        break;
                    case 3: // Numbers
                        result = TextMetrics.countNumbers(inputText);
                        break;
                }

                txtResult.setText(getString(R.string.result_format, result));
            }
        });
    }
}
