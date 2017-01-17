package com.example.nameapp;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        final String[] names = {"Alpha", "Beta", "Gamma", "Delta"
                            , "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa"};


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            long mLastClickTime = 0;

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;

                }

                //Klikk
                mLastClickTime = SystemClock.elapsedRealtime();


                Toast.makeText(Gallery.this, "" + names[position], Toast.LENGTH_SHORT).show();
            }
        });





    }
}
