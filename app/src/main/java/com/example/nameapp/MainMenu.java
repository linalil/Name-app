package com.example.nameapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();

        //Fjernar logo dersom telefonen er i landskapsmodus.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            View my_view = findViewById(R.id.imageView2);
            my_view.setVisibility(View.GONE);
        }
    }

    //Startar ny aktivitet som følgje av trykk på Name List-knapp.
    public void openNameList(View view) {
        Intent intent;
        intent = new Intent(this, NameList.class);
        startActivity(intent);
    }

    //Startar ny aktivitet som følgje av trykk på Gallery-knapp.
    public void openGallery(View view) {

        Intent intent;
        intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }

    //Startar ny aktivitet som følgje av trykk på Learning Mode-knapp.
    public void openLearningMode(View view) {

        Intent intent;
        intent = new Intent(this, LearningMode.class);
        startActivity(intent);

    }

}
