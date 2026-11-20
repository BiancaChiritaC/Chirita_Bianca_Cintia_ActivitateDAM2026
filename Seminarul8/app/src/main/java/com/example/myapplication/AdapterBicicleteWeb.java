package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterBicicleteWeb extends ArrayAdapter<ImaginiWeb> {

    public AdapterBicicleteWeb( Context context,ArrayList<ImaginiWeb> lista ) {
        super(context,0,lista);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_lista_web, parent, false);
        }

        ImaginiWeb imagine = getItem(position);
        ImageView imgBicicleta = convertView.findViewById(R.id.Imagine);
        TextView tvTitlu = convertView.findViewById(R.id.tvTitluImg);
        TextView tvDescriere = convertView.findViewById(R.id.tvDescriere);

        if (imagine != null) {

            tvTitlu.setText(imagine.getTitlu());
            tvDescriere.setText(imagine.getDescriere());

            Glide.with(getContext()).load(imagine.getImagine()).centerCrop().placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(imgBicicleta);
    }

        return convertView;
}
}
