package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaBicicleta extends AppCompatActivity {

    public static final String CHEIE_BICICLETA = "bicicleta";

    private EditText etMarca;
    private RadioButton rbMunte, rbStrada, rbTeren;
    private CheckBox cbStoc;
    private Spinner spCuloare;
    private Switch swElectrica;
    private RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_bicicleta);

        etMarca = findViewById(R.id.etMarca);
        rbMunte = findViewById(R.id.rbMunte);
        rbStrada = findViewById(R.id.rbStrada);
        rbTeren = findViewById(R.id.rbTeren);
        cbStoc = findViewById(R.id.cbStoc);
        spCuloare = findViewById(R.id.spCuloare);
        swElectrica = findViewById(R.id.swElectrica);
        rbRating = findViewById(R.id.rbRating);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.culori, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spCuloare.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void adaugaBicicleta(View view){
        String marca = etMarca.getText().toString().trim();
        if (TextUtils.isEmpty(marca)) {
            etMarca.setError("Introduceți marca!");
            return;
        }

        String tip;
        if (rbMunte.isChecked()) {
            tip = "Bicicleta de munte";

        } else if (rbStrada.isChecked()) {
            tip = "Bicicleta pentru strada";

        } else {
            tip = "Bicicleta de teren";

        }

        Bicicleta bicicleta = new Bicicleta(
                marca,
                tip,
                cbStoc.isChecked(),
                spCuloare.getSelectedItem().toString(),
                swElectrica.isChecked(),
                rbRating.getRating());

        Intent rezultat = new Intent();
        rezultat.putExtra(CHEIE_BICICLETA, bicicleta); // Serializable, în Bundle-ul Intent-ului
        setResult(RESULT_OK, rezultat);
        finish();

    }
}