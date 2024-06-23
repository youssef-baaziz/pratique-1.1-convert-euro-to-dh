package net.baaziz.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private TextView textViewResult;
    private ListView listViewHistory;
    private ArrayList<String> conversionHistory;
    private ArrayAdapter<String> historyAdapter;

    private static final double EXCHANGE_RATE = 10.5; // Example exchange rate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        textViewResult = findViewById(R.id.textViewResult);
        listViewHistory = findViewById(R.id.listViewHistory);
        Button buttonConvert = findViewById(R.id.buttonConvert);

        conversionHistory = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conversionHistory);
        listViewHistory.setAdapter(historyAdapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = editTextAmount.getText().toString();
                if (!amountStr.isEmpty()) {
                    double amountEuro = Double.parseDouble(amountStr);
                    double amountDh = amountEuro * EXCHANGE_RATE;
                    String result = String.format("%.2f Euro = %.2f DH", amountEuro, amountDh);
                    textViewResult.setText(result);

                    // Add to history
                    conversionHistory.add(result);
                    historyAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
