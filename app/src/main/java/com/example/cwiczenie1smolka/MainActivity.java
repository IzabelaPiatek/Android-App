package com.example.cwiczenie1smolka;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText imie;
    EditText nazwisko;
    EditText ocena;
    Button ocenaB;
    double srednia = 0.0;
    TextView napis;

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("imie", imie.getText().toString());
        outState.putString("nazwisko", nazwisko.getText().toString());
        outState.putString("liczbaOcen", ocena.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        imie.setText(savedInstanceState.getString("Imie"));
        nazwisko.setText(savedInstanceState.getString("Nazwisko"));
        ocena.setText(savedInstanceState.getString("Liczba ocen"));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imie = findViewById(R.id.imieText);
        nazwisko = findViewById(R.id.nazwiskoText);
        ocena = findViewById(R.id.liczbaocenText);
        ocenaB = findViewById(R.id.oceny_b);
        napis = findViewById(R.id.napis);

        imie.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (imie.length() == 0 && hasFocus == false) {
                    String komunikat = "Imie nie moze byc puste";
                    Toast imie = Toast.makeText(MainActivity.this, komunikat.toString(), Toast.LENGTH_SHORT);
                    imie.show();
                }
            }
        });
        nazwisko.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (nazwisko.length() == 0 && hasFocus == false) {
                    String komunikat = "Nazwisko nie moze byc puste";
                    Toast imie = Toast.makeText(MainActivity.this, komunikat.toString(), Toast.LENGTH_SHORT);
                    imie.show();
                }
            }
        });
        ocena.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ocena.length() == 0 && hasFocus == false) {
                    String komunikat = "Ocena nie moze byc pusta";
                    Toast imie = Toast.makeText(MainActivity.this, komunikat.toString(), Toast.LENGTH_SHORT);
                    imie.show();
                }
            }
        });


        imie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sprawdz();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nazwisko.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sprawdz();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ocena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sprawdz();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ocena.length() != 0 && Integer.parseInt(ocena.getText().toString()) < 5) {
                    String komunikat = "Liczba ocen nie moze byc mniejsza niz 5";
                    Toast imie = Toast.makeText(MainActivity.this, komunikat.toString(), Toast.LENGTH_SHORT);
                    imie.show();
                } else if (ocena.length() != 0 && Integer.parseInt(ocena.getText().toString()) > 15) {
                    String komunikat = "Liczba ocen nie moze byc mniejsza niz 15";
                    Toast imie = Toast.makeText(MainActivity.this, komunikat.toString(), Toast.LENGTH_SHORT);
                    imie.show();
                }

            }
        });
        wywolajAktywnosc(ocenaB);
    }

    private void sprawdz() {
        if (imie.length() != 0 && nazwisko.length() != 0 && ocena.length() != 0 && (Integer.parseInt(ocena.getText().toString()) <= 15) && (Integer.parseInt(ocena.getText().toString()) >= 5)) {
            ocenaB.setVisibility(View.VISIBLE);
        } else {
            ocenaB.setVisibility(View.INVISIBLE);
        }
    }

    private void wywolajAktywnosc(Button przycisk) {
        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zamiar = new Intent(MainActivity.this, ListaOcenActivity.class);
                zamiar.putExtra("etykieta", Integer.parseInt(ocena.getText().toString()));
                startActivityForResult(zamiar, 1);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle tobolek = data.getExtras();
            srednia = tobolek.getDouble("srednia");
            napis.setVisibility(View.VISIBLE);
            napis.setText("Twoja średnia to " + String.format("%.2f%n", srednia));
            if (srednia >= 3) {
                ocenaB.setText("Super :)");
                komunikatKoncowy("Gratulacje! Otrzymujesz Zaliczenie!");
            } else {
                ocenaB.setText("Niestety tym razem mi nie poszło");
                komunikatKoncowy("Wysyłam podanie o zaliczenie warunkowe");
            }
        }
        imie.setEnabled(false);
        nazwisko.setEnabled(false);
        ocena.setEnabled(false);

    }


    private void komunikatKoncowy(final String komunikat) {
        ocenaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, komunikat, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
