package com.example.nameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void openNameList(View view) {

        Intent intent;
        intent = new Intent(this, NameList.class);
        startActivity(intent);
    }

    public void openGallery(View view) {

        Intent intent;
        intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }

    public void openLearningMode(View view) {

        Intent intent;
        intent = new Intent(this, LearningMode.class);
        startActivity(intent);

    }

}
