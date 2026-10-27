package com.example.seminar2_1076;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Seminar2","onStart");
        Log.w("Seminar2","onStart");
        Log.d("Seminar2","onStart");
        Log.i("Seminar2","onStart");
        Log.v("Seminar2","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Seminar2","onResume");
        Log.w("Seminar2","onResume");
        Log.d("Seminar2","onResume");
        Log.i("Seminar2","onResume");
        Log.v("Seminar2","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Seminar2","onPause");
        Log.w("Seminar2","onPause");
        Log.d("Seminar2","onPause");
        Log.i("Seminar2","onPause");
        Log.v("Seminar2","onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Seminar2","onStop");
        Log.w("Seminar2","onStop");
        Log.d("Seminar2","onStop");
        Log.i("Seminar2","onStop");
        Log.v("Seminar2","onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Seminar2","onDestroy");
        Log.w("Seminar2","onDestroy");
        Log.d("Seminar2","onDestroy");
        Log.i("Seminar2","onDestroy");
        Log.v("Seminar2","onDestroy");
    }
}