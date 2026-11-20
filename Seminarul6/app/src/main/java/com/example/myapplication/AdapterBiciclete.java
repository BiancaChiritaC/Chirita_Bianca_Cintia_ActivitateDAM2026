
package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBiciclete extends ArrayAdapter<Bicicleta> {

    public AdapterBiciclete(Context context, ArrayList<Bicicleta> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.afisare_biciclete, parent, false);
        }

        Bicicleta bicicleta = getItem(position);

        TextView tvMarca = convertView.findViewById(R.id.tvMarcaB);
        TextView tvTip = convertView.findViewById(R.id.tvTipB);
        TextView tvCuloare = convertView.findViewById(R.id.tvCuloareB);
        TextView tvRating = convertView.findViewById(R.id.tvRatingB);

        if (bicicleta != null) {
            tvMarca.setText(bicicleta.getMarca());
            tvTip.setText("Tip: " + bicicleta.getTip());
            tvCuloare.setText("Culoare: " + bicicleta.getCuloare());
            tvRating.setText("Ratingul bicicletei: " + bicicleta.getRating() + "/5");
        }

        return convertView;

    }
}
