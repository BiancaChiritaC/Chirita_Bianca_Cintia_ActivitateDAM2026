package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetaliiBicicleta extends AppCompatActivity {

    private WebView webViewBicicleta;
    private TextView tvTitluDetalii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_bicicleta);

        webViewBicicleta = findViewById(R.id.webViewBicicleta);
        tvTitluDetalii = findViewById(R.id.tvTitluDetalii);

        String url = getIntent().getStringExtra("urlPagina");
        String titlu = getIntent().getStringExtra("titlu");

        tvTitluDetalii.setText(titlu);
        webViewBicicleta.setWebViewClient(new WebViewClient());

        if (url != null && !url.isEmpty()) {
            webViewBicicleta.loadUrl(url);
        }
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webViewBicicleta != null && webViewBicicleta.canGoBack()) {
                    webViewBicicleta.goBack();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                    setEnabled(true);
                }
            }
        });
    }

    public void inchideDetalii(View view) {
        finish();
    }

}