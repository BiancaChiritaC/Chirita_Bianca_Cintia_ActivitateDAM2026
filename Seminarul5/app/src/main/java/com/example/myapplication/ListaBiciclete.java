package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaBiciclete extends AppCompatActivity {

    private ArrayList<Bicicleta> listaBiciclete;
    private ArrayAdapter<Bicicleta> adapter;
    private ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biciclete);

        lstView = findViewById(R.id.listView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listaBiciclete = (ArrayList<Bicicleta>)getIntent().getSerializableExtra(MainActivity.CHEIE_LISTA, ArrayList.class);
        } else {
            listaBiciclete = (ArrayList<Bicicleta>)getIntent().getSerializableExtra(MainActivity.CHEIE_LISTA);
        }

        if (listaBiciclete == null) {
            listaBiciclete = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaBiciclete);
        lstView.setAdapter(adapter);

        lstView.setOnItemClickListener((parent, view, position, id) -> {
                    Bicicleta bicicleta = listaBiciclete.get(position);
                    Toast.makeText(ListaBiciclete.this, bicicleta.toString(), Toast.LENGTH_LONG).show();
                });

        lstView.setOnItemLongClickListener((parent, view, position, id) -> {
                    Bicicleta bicicleta = listaBiciclete.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ListaBiciclete.this, "Bicicleta " + bicicleta.getMarca() + " a fost stearsa!", Toast.LENGTH_SHORT).show();

                    return true;
                });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.CHEIE_LISTA, listaBiciclete);
        setResult(RESULT_OK, intent);

        super.finish();
    }

    public void InchidereLista(View view) {
        finish();
    }
}