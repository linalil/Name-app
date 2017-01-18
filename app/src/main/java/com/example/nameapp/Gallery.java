package com.example.nameapp;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.nameapp.PersonList.getListe;
import static com.example.nameapp.PersonList.listInitialized;
import static com.example.nameapp.PersonList.initialize;
import static com.example.nameapp.PersonList.listInitialized;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if(!listInitialized()){
            initialize();
        }
        final ArrayList<Person> p = getListe();

        GridView grid = (GridView) findViewById(R.id.gridview);
        grid.setAdapter(new ImageAdapter(this, R.layout.activity_gallery_imageview, p));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            long mLastClickTime = 0;

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }

                //Klikk
                mLastClickTime = SystemClock.elapsedRealtime();


                Toast.makeText(Gallery.this, "" +  p.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
