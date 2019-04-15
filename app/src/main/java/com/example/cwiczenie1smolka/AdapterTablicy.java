package com.example.cwiczenie1smolka;

import android.app.Activity;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterTablicy extends ArrayAdapter<ModelOceny> {
    private List<ModelOceny> listaOcen;
    private Activity kontekst;
    View widok = null;


    public AdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        super(kontekst, R.layout.wiersz_oceny, listaOcen);
        this.kontekst = kontekst;
        this.listaOcen = listaOcen;

    }

    public View getView(int numerWiersza, View widokDoRecyklingu, ViewGroup parent) {

        if (widokDoRecyklingu == null) {
            LayoutInflater pompka = kontekst.getLayoutInflater();
            widok = pompka.inflate(R.layout.wiersz_oceny, null);
            RadioGroup grupaOceny = widok.findViewById(R.id.grupaOcen);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup grupaOceny, int idWybranegoButtona) {
                            ModelOceny element = (ModelOceny) grupaOceny.getTag();
                            switch (idWybranegoButtona) {
                                case R.id.radioOcena2:
                                    element.setOcena(2);
                                    break;
                                case R.id.radioOcena3:
                                    element.setOcena(3);
                                    break;
                                case R.id.radioOcena4:
                                    element.setOcena(4);
                                    break;
                                case R.id.radioOcena5:
                                    element.setOcena(5);
                                    break;
                            }
                        }
                    });

            grupaOceny.setTag(listaOcen.get(numerWiersza));

        } else {
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny = widok.findViewById(R.id.grupaOcen);
            grupaOceny.setTag(listaOcen.get(numerWiersza));
        }
        TextView etykieta = widok.findViewById(R.id.textView);
        etykieta.setText(listaOcen.get(numerWiersza).getNazwa());
        RadioGroup grupaOceny = widok.findViewById(R.id.grupaOcen);
        ustawOcene(grupaOceny, numerWiersza);
        return widok;
    }

    private void ustawOcene(RadioGroup grupaOceny, int numerWiersza) {
        switch (listaOcen.get(numerWiersza).getOcena()) {
            case 2:
                grupaOceny.check(R.id.radioOcena2);
                break;
            case 3:
                grupaOceny.check(R.id.radioOcena3);
                break;
            case 4:
                grupaOceny.check(R.id.radioOcena4);
                break;
            case 5:
                grupaOceny.check(R.id.radioOcena5);
                break;
        }
    }


}
