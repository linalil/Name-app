package com.example.nameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //TODO: Sjekk om det er første gang appen opnast, lag i så fall owner.

        /*Pseudokode, google om en må kode her for å få splashscreen til å "holde".

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if(settings.contains("owner"){
            .... intent som opnar mainmenu
        }
        else{
            intent som opnar addOwner
        }
        *
        * */





    }




}
