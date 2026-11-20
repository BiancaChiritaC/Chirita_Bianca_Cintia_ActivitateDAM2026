package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaBiciclete extends AppCompatActivity {

    private ArrayList<Bicicleta> listaBiciclete;
    private AdapterBiciclete adapter;
    private ListView lstView;
    private int pozitieSelectata = -1;

    private ActivityResultLauncher<Intent> editareLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_biciclete);

        lstView = findViewById(R.id.listView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listaBiciclete = getIntent().getParcelableArrayListExtra(
                    MainActivity.CHEIE_LISTA, Bicicleta.class);
        } else {
            listaBiciclete = getIntent().getParcelableArrayListExtra(
                    MainActivity.CHEIE_LISTA);
        }

        if (listaBiciclete == null) {
            listaBiciclete = new ArrayList<>();
        }

        adapter = new AdapterBiciclete(this, listaBiciclete);
        lstView.setAdapter(adapter);

        editareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK
                            && result.getData() != null
                            && pozitieSelectata != -1) {

                        Bicicleta bicicletaModificata;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            bicicletaModificata = result.getData().getParcelableExtra(
                                    AdaugaBicicleta.CHEIE_BICICLETA,
                                    Bicicleta.class);
                        } else {
                            bicicletaModificata = result.getData().getParcelableExtra(
                                    AdaugaBicicleta.CHEIE_BICICLETA);
                        }

                        if (bicicletaModificata != null) {
                            Bicicleta bicicletaExistenta =
                                    listaBiciclete.get(pozitieSelectata);

                            bicicletaExistenta.setMarca(bicicletaModificata.getMarca());
                            bicicletaExistenta.setTip(bicicletaModificata.getTip());
                            bicicletaExistenta.setInStoc(bicicletaModificata.isInStoc());
                            bicicletaExistenta.setCuloare(bicicletaModificata.getCuloare());
                            bicicletaExistenta.setEsteElectrica(
                                    bicicletaModificata.isEsteElectrica());
                            bicicletaExistenta.setRating(bicicletaModificata.getRating());

                            adapter.notifyDataSetChanged();

                            Toast.makeText(this,
                                    "Bicicleta a fost modificată!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        pozitieSelectata = -1;
                    }
                });

        lstView.setOnItemClickListener((parent, view, position, id) -> {
            pozitieSelectata = position;

            Bicicleta bicicleta = listaBiciclete.get(position);

            Intent intent = new Intent(ListaBiciclete.this, AdaugaBicicleta.class);
            intent.putExtra(AdaugaBicicleta.CHEIE_BICICLETA, bicicleta);
            intent.putExtra(AdaugaBicicleta.CHEIE_MODIFICARE, true);

            editareLauncher.launch(intent);
        });


        lstView.setOnItemLongClickListener((parent, view, position, id) -> {
            Bicicleta bicicleta = listaBiciclete.remove(position);

            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Bicicleta " + bicicleta.getMarca() + " a fost ștearsă!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(
                MainActivity.CHEIE_LISTA, listaBiciclete);

        setResult(RESULT_OK, intent);
        super.finish();
    }

    public void InchidereLista(View view) {
        finish();
    }
}