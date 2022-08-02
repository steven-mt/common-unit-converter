package au.edu.jcu.cp3406.commonunitsconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editCm;
    private EditText editFt;
    private EditText editIn;
    private TextView textCm;
    private TextView textFt;
    private TextView textIn;

    private EditText editCelsius;
    private EditText editFahrenheit;
    private TextView textCelsius;
    private TextView textFahrenheit;

    private EditText editKg;
    private EditText editLb;
    private TextView textKg;
    private TextView textLb;

    private EditText editKm;
    private EditText editMi;
    private TextView textKm;
    private TextView textMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        AppCompatDelegate.setDefaultNightMode(preferences.getInt("default night mode",
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));

        editCm = findViewById(R.id.edit_cm);
        editFt = findViewById(R.id.edit_ft);
        editIn = findViewById(R.id.edit_in);
        textCm = findViewById(R.id.text_cm);
        textFt = findViewById(R.id.text_ft);
        textIn = findViewById(R.id.text_in);

        editCelsius = findViewById(R.id.edit_celsius);
        editFahrenheit = findViewById(R.id.edit_fahrenheit);
        textCelsius = findViewById(R.id.text_celsius);
        textFahrenheit = findViewById(R.id.text_fahrenheit);

        editKg = findViewById(R.id.edit_kg);
        editLb = findViewById(R.id.edit_lb);
        textKg = findViewById(R.id.text_kg);
        textLb = findViewById(R.id.text_lb);

        editKm = findViewById(R.id.edit_km_h);
        editMi = findViewById(R.id.edit_mi_h);
        textKm = findViewById(R.id.text_km_h);
        textMi = findViewById(R.id.text_mi_h);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editCm.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);

                    double ftResult = Converter.getCmToFtAndIn(value, "ft", false);
                    textFt.setText(String.valueOf((int) ftResult));

                    double inRemainder = Converter.getCmToFtAndIn(value, "in", true);
                    textIn.setText(String.format(Locale.getDefault(), "%.2f", inRemainder));
                } else {
                    textFt.setText(R.string.hyphen);
                    textIn.setText(R.string.hyphen);
                }

                String stringFt = editFt.getText().toString();
                String stringIn = editIn.getText().toString();
                double valueFt, valueIn;
                valueFt = valueIn = 0;
                if (!stringFt.isEmpty() || !stringIn.isEmpty()) {
                    if (!stringFt.isEmpty()) {
                        valueFt = Double.parseDouble(stringFt);
                    }
                    if (!stringIn.isEmpty()) {
                        valueIn = Double.parseDouble(stringIn);
                    }
                    double cmResult = Converter.getFtOrInToCm(valueFt, "ft")
                            + Converter.getFtOrInToCm(valueIn, "in");
                    textCm.setText(String.format(Locale.getDefault(), "%.2f", cmResult));
                } else {
                    textCm.setText(R.string.hyphen);
                }

                text = editCelsius.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double fResult = Converter.getCelsiusToFahrenheit(value);
                    textFahrenheit.setText(String.format(Locale.getDefault(), "%.2f", fResult));
                } else {
                    textFahrenheit.setText(R.string.hyphen);
                }

                text = editFahrenheit.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double cResult = Converter.getFahrenheitToCelsius(value);
                    textCelsius.setText(String.format(Locale.getDefault(), "%.2f", cResult));
                } else {
                    textCelsius.setText(R.string.hyphen);
                }

                text = editKg.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double lbResult = Converter.getKgToLb(value);
                    textLb.setText(String.format(Locale.getDefault(), "%.2f", lbResult));
                } else {
                    textLb.setText(R.string.hyphen);
                }

                text = editLb.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double kgResult = Converter.getLbToKg(value);
                    textKg.setText(String.format(Locale.getDefault(), "%.2f", kgResult));
                } else {
                    textKg.setText(R.string.hyphen);
                }

                text = editKm.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double miResult = Converter.getKmToMi(value);
                    textMi.setText(String.format(Locale.getDefault(), "%.2f", miResult));
                } else {
                    textMi.setText(R.string.hyphen);
                }

                text = editMi.getText().toString();
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    double kmResult = Converter.getMiToKm(value);
                    textKm.setText(String.format(Locale.getDefault(), "%.2f", kmResult));
                } else {
                    textKm.setText(R.string.hyphen);
                }
            }
        };

        /*
         Iterate through each child of each row in the table, and add the listener if it is an
         EditText object.
         */
        ViewGroup tableLayout = findViewById(R.id.table);
        for (int i = 0; i < tableLayout.getChildCount(); ++i) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); ++j) {
                View childView = tableRow.getChildAt(j);
                if (childView instanceof EditText) {
                    ((EditText) childView).addTextChangedListener(textWatcher);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}