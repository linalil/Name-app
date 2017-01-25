package com.example.nameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                if(settings.contains("owner")){
                    Intent intent = new Intent(SplashScreen.this, MainMenu.class);
                    startActivity(intent);

                    finish();
                }
                else{
                    Intent intent = new Intent(SplashScreen.this, addOwner.class);
                    startActivity(intent);

                    finish();
                }
            }
        }, SPLASH_TIME_OUT);


        //TODO: Sjekk om det er første gang appen opnast, lag i så fall owner.






    }




}
