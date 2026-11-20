
package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String CHEIE_LISTA = "lista_biciclete";

    private ArrayList<Bicicleta> lstBiciclete = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            Bicicleta b;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                b = result.getData().getParcelableExtra(AdaugaBicicleta.CHEIE_BICICLETA, Bicicleta.class);
                            } else {
                                b = result.getData().getParcelableExtra(AdaugaBicicleta.CHEIE_BICICLETA);
                            }

                            if (b != null) {
                                lstBiciclete.add(b);
                                Toast.makeText(this, b.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );

    private final ActivityResultLauncher<Intent> launcherBiciclete =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                lstBiciclete = result.getData().getParcelableArrayListExtra(CHEIE_LISTA, Bicicleta.class);
                            } else {
                                lstBiciclete = result.getData().getParcelableArrayListExtra(CHEIE_LISTA);
                            }

                            if (lstBiciclete == null) {
                                lstBiciclete = new ArrayList<>();
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                lstBiciclete = savedInstanceState.getParcelableArrayList(CHEIE_LISTA, Bicicleta.class);
            } else {
                lstBiciclete = savedInstanceState.getParcelableArrayList(CHEIE_LISTA);
            }
        }

        if (lstBiciclete == null) {
            lstBiciclete = new ArrayList<>();
        }

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {

                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

                    return insets;
                });
    }


    public void activitateNoua(View view) {

        Intent it = new Intent(MainActivity.this, AdaugaBicicleta.class);
        launcher.launch(it);
    }

    public void deschideLista(View view) {

        Intent intent = new Intent(MainActivity.this, ListaBiciclete.class);
        intent.putParcelableArrayListExtra(CHEIE_LISTA, lstBiciclete);

        launcherBiciclete.launch(intent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CHEIE_LISTA, lstBiciclete);
        super.onSaveInstanceState(outState);
    }
}
