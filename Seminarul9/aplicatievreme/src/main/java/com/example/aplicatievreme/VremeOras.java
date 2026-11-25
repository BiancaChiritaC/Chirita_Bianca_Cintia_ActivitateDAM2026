package com.example.aplicatievreme;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class VremeOras extends AppCompatActivity {
    private final String API_KEY = "INTRODU_API_KEY";
    private EditText etVreme;
    private Spinner spPerioada;
    private Button btnAfisare;
    private TextView tvRezultat;
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vreme_oras);

        etVreme=findViewById(R.id.etOras);
        spPerioada = findViewById(R.id.spPerioada);
        btnAfisare=findViewById(R.id.btnAfisare);
        tvRezultat=findViewById(R.id.tvRezultat);

        String[] perioade = {"1 zi", "5 zile"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, perioade);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPerioada.setAdapter(adapter);

        btnAfisare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oras = etVreme.getText().toString().trim();
                if (oras.isEmpty()) {
                    Toast.makeText(VremeOras.this, "Introduceti numele unui oras!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean Perioada5Zile = (spPerioada.getSelectedItemPosition() == 1);
                    CitySearch(oras, Perioada5Zile);
                }
            }
        });


    }

    public void CitySearch(String oras, boolean Perioada5Zile) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                StringBuilder rezultatFinal = new StringBuilder();

                try {
                    String link = "https://dataservice.accuweather.com/locations/v1/cities/search" + "?apikey=" + API_KEY + "&q=" + URLEncoder.encode(oras, "UTF-8");

                    String raspunsOras = executaCerereGET(link);
                    JSONArray Orase = new JSONArray(raspunsOras);

                    if (Orase.length() > 0) {
                        JSONObject obiectOras = Orase.getJSONObject(0);
                        String cheieOras = obiectOras.getString("Key");
                        String numeOras = obiectOras.getString("LocalizedName");

                        rezultatFinal.append("Oras: ").append(numeOras).append(" (Cheie: ").append(cheieOras).append(")\n\n");
                        String tipPrognoza = Perioada5Zile ? "5day" : "1day";
                        String linkPrognoza = "https://dataservice.accuweather.com/forecasts/v1/daily/" + tipPrognoza + "/" + cheieOras + "?apikey=" + API_KEY + "&metric=true";
                        String raspunsPrognoza = executaCerereGET(linkPrognoza);

                        JSONObject obiectPrincipal = new JSONObject(raspunsPrognoza);
                        JSONArray dailyForecasts = obiectPrincipal.getJSONArray("DailyForecasts");

                        for (int i = 0; i < dailyForecasts.length(); i++) {
                            JSONObject zi = dailyForecasts.getJSONObject(i);
                            String data = zi.getString("Date").substring(0, 10);

                            JSONObject temperature = zi.getJSONObject("Temperature");
                            JSONObject minimum = temperature.getJSONObject("Minimum");
                            JSONObject maximum = temperature.getJSONObject("Maximum");

                            double tempMin = minimum.getDouble("Value");
                            String unitMin = minimum.getString("Unit");

                            double tempMax = maximum.getDouble("Value");
                            String unitMax = maximum.getString("Unit");

                            rezultatFinal.append("Data: ").append(data).append("\n").append("  Minima: ").append(tempMin).append(" °").append(unitMin).append("\n").append("  Maxima: ").append(tempMax).append(" °").append(unitMax).append("\n\n");
                        }

                    } else {
                        rezultatFinal.append("Orasul nu a fost gasit!");
                    }

                } catch (Exception e) {
                    rezultatFinal = new StringBuilder("Eroare: " + e.getMessage());
                }

                String deAfisat = rezultatFinal.toString();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvRezultat.setText(deAfisat);
                    }
                });
            }
        });
    }

    private String executaCerereGET(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection conexiune = (HttpURLConnection) url.openConnection();
        conexiune.setRequestMethod("GET");

        int codRaspuns = conexiune.getResponseCode();
        if (codRaspuns == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexiune.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String linie;
            while ((linie = reader.readLine()) != null) {
                builder.append(linie);
            }
            reader.close();
            conexiune.disconnect();
            return builder.toString();
        } else {
            conexiune.disconnect();
            throw new Exception("Eroare HTTP " + codRaspuns);
        }
    }
}


