package com.example.nameapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.initialize;
import static com.example.nameapp.HelperClass.listInitialized;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().hide();

        //Sjekkar om lista med personar er initialisert, og initialiserer dersom ikkje
        if(!listInitialized()){
            initialize(this);
        }

        //Hentar inn lista over personar
        final ArrayList<Person> p = getListe();

        //Opprettar ein Grid som vi så fyller med bilete via ImageAdapter-klassen.
        //Sender inn lista 'p' som parameter.
        GridView grid = (GridView) findViewById(R.id.gridview);
        grid.setAdapter(new ImageAdapter(this, R.layout.activity_gallery_imageview, p));

        //Lyttar som registrerer om ein trykker på eit bilete i galleriet.
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            long mLastClickTime = 0;

            //Metode som fortel kva som skal skje ved tastetrykk
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //Hindrar at raske tastetrykk etter kvarandre skal bli registrert.
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                //Lagar ein liten pop-up tekst med namnet til personen når ein trykker på biletet.
                Toast.makeText(Gallery.this, "" +  p.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Metode som går tilbake ved Back-knapp.
    public void goBack(View view) {

        Intent intent;
        intent = new Intent(this, MainMenu.class);
        startActivity(intent);

    }
}
