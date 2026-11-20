package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bicicleta implements Parcelable {
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

    protected Bicicleta(Parcel in) {
        marca = in.readString();
        tip = in.readString();
        inStoc = in.readByte() != 0;
        culoare = in.readString();
        esteElectrica = in.readByte() != 0;
        rating = in.readFloat();
    }

    public static final Creator<Bicicleta> CREATOR = new Creator<Bicicleta>() {
        @Override
        public Bicicleta createFromParcel(Parcel in) {
            return new Bicicleta(in);
        }

        @Override
        public Bicicleta[] newArray(int size) {
            return new Bicicleta[size];
        }
    };

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

    public boolean isInStoc() { return inStoc; }

    public boolean isEsteElectrica() { return esteElectrica; }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setInStoc(boolean inStoc) {
        this.inStoc = inStoc;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public void setEsteElectrica(boolean esteElectrica) {
        this.esteElectrica = esteElectrica;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bicicleta: Marca=" + marca + ", tip='" + tip + ", " + inStoc
                + ", culoarea " + culoare + ", " + esteElectrica
                + ", rating " + rating ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(tip);
        dest.writeByte((byte)(inStoc? 1:0));
        dest.writeString(culoare);
        dest.writeByte((byte) (esteElectrica? 1:0));
        dest.writeFloat(rating);
    }
}
