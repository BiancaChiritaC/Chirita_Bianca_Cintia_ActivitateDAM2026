package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaBicicleteWeb extends AppCompatActivity {

    private ListView lstBWeb;
    private ArrayList<ImaginiWeb> imaginiWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_biciclete_web);

        lstBWeb=findViewById(R.id.lstVImagini);
        imaginiWeb=new ArrayList<>();
        imaginiWeb.add(new ImaginiWeb("Bicileta de munte","Bicicleta de munte, de culoare rosie,cu schimb de viteze buna pentru terenuri abrupte, pietruite de munte.","https://www.leaderfox.ro/wp-content/uploads/BICICL1-9-300x300.jpg","https://www.leaderfox.ro/bicicleta-mtb-29-full-suspension-leader-fox-trion-schimbator-shimano-10-viteze/"));
        imaginiWeb.add(new ImaginiWeb("Bicicleta electrica de munte","Bicicleta electrică de munte este echipată cu 2 motoare cu butuc fără perii de mare viteză care oferă multă putere pentru a aborda dealurile abrupte de până la 35 de grade si ajută la mersul pe munte cu usurintă spre dealuri,trasee de pietris, poteci de pământ si drumuri accidentate.","https://s1.cel.ro/images/mari/2024/04/24/bicicleta-electrica-de-munte-gunai-gn88-26-and-4-0-anvelopa-grasa-1000w2-motoare-55kmh-viteza-maxima-48v-23ah-baterie-100km-autonomie-gu-7113dc61.jpg","https://s1.cel.ro/images/mari/2024/04/24/bicicleta-electrica-de-munte-gunai-gn88-26-and-4-0-anvelopa-grasa-1000w2-motoare-55kmh-viteza-maxima-48v-23ah-baterie-100km-autonomie-gu-7113dc61.jpg"));
        imaginiWeb.add(new ImaginiWeb("Bicicleta electrica","Bicicleta cu bateria detașabilă de 15 Ah poate fi încărcată pe cadru sau în afara acestuia. Sistemul BMS protejează bateria, mărind durata de viață. ","https://www.piwishop.ro/wp-content/uploads/2024/08/11-5-1.jpg","https://www.piwishop.ro/produs/bicicleta-electrica-duotts-c29-29-inch-cu-2-baterii/"));
        imaginiWeb.add(new ImaginiWeb("Bicicleta Street","În cazul unei biciclete de dirt jump contemporane, atent realizate, simplitatea este un lucru la care trebuie să aspirăm. Am selectat materialul ideal pentru Point - oțelul; are o senzație magică și este incredibil de rezistent.","https://www.bikehouse.ro/image/cache/catalog/Pivot_Cycles/00PNTV226ADJSD_DKBOSSTDHN-580x580.png","https://www.bikehouse.ro/bicicleta-street-dirt-pivot-cycles-point-dusk-blue-2026"));
        imaginiWeb.add(new ImaginiWeb("Bicicleta Bimbo","Bicicleta Bimbo Bike este conceputa special pentru copiii cu varsta intre 6 si 9 ani, oferind un echilibru foarte bun intre siguranta, confort si usurinta in utilizare. Culoarea roz si designul modern o fac atractiva pentru cei mici, iar echiparea de tip MTB este potrivita pentru plimbari zilnice, in parc sau pe alei.","https://cdn.dedeman.ro/media/catalog/product/7/0/7080359.jpg?optimize=low&fit=bounds&height=&width=&canvas=","https://www.dedeman.ro/ro/bicicleta-copii-6-9-ani-mtb-bimbo-bike-20-inch-6-viteze-frane-v-brake-roz/p/7080359?srsltid=AYCX9vLdlUu4-dPoIpR6DJPtKppeGyb8kSSk05a9oOyA5rYurPktZLsW"));

        AdapterBicicleteWeb adapter= new AdapterBicicleteWeb(this,imaginiWeb);
        lstBWeb.setAdapter(adapter);
        lstBWeb.setOnItemClickListener((parent, view, position, id) -> {
            ImaginiWeb imagine = imaginiWeb.get(position);

            Intent intent = new Intent(this, DetaliiBicicleta.class);
            intent.putExtra("urlPagina", imagine.getSiteWeb());
            intent.putExtra("titlu", imagine.getTitlu());

            startActivity(intent);
        });
    }
    public void inchideListaWeb(View view) {
        finish();
    }
}