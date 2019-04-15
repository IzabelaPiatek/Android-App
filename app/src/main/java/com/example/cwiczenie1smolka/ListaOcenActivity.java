package com.example.cwiczenie1smolka;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaOcenActivity extends AppCompatActivity {
    ArrayList<ModelOceny> dane;
    ListView listaOcen;
    int liczba;
    Button gotowe;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < dane.size(); i++)
            outState.putInt(String.valueOf(i),
                    dane.get(i).getOcena());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < dane.size(); i++) {
            dane.get(i).setOcena(savedInstanceState.getInt(String.valueOf(i)));
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Bundle tobolek = getIntent().getExtras();
        liczba = tobolek.getInt("etykieta");
        gotowe = findViewById(R.id.gotowe);
        dane = new ArrayList<>();

        for (int i = 0; i < liczba; i++)
            dane.add(new ModelOceny("Ocena " + String.valueOf(i + 1)));
        final AdapterTablicy adapter = new AdapterTablicy(this, dane);
        listaOcen = findViewById(R.id.listaOcen);
        listaOcen.setAdapter(adapter);

        gotowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double srednia = 0.0;
                for (ModelOceny ocena : dane) {
                    srednia += ocena.getOcena();
                }
                srednia = srednia / liczba;
                Bundle tobolek = new Bundle();
                tobolek.putDouble("srednia", srednia);
                Intent zamiar = new Intent();
                zamiar.putExtra("srednia", srednia);
                setResult(RESULT_OK, zamiar);
                finish();
            }
        });
    }


}
