package com.example.myapplication;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Bicicleta implements Serializable {
    private String marca;
    private String tip;
    private boolean inStoc;
    private String culoare;
    private boolean esteElectrica;
    private float rating;

    public Bicicleta(String marca, String tip, boolean inStoc, String culoare,boolean esteElectrica, float rating){
        this.marca=marca;
        this.tip=tip;
        this.inStoc=inStoc;
        this.culoare=culoare;
        this.esteElectrica=esteElectrica;
        this.rating=rating;
    }
    public String getMarca() {
        return marca;
    }

    public String getTip() {
        return tip;
    }

    public String getCuloare() {
        return culoare;
    }

    public float getRating() {
        return rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bicicleta: Marca=" + marca + ", tip='" + tip + ", " + inStoc
                + ", culoarea " + culoare + ", " + esteElectrica
                + ", rating " + rating ;
    }
}
