package com.example.nameapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class LearningMode extends AppCompatActivity {

    GameCenter gameCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

        gameCenter = new GameCenter(this);
        gameCenter.nextPicture();

        updatePicture();

    }

    public void sendAnswer(View view) {
       // Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        editText.setText("");
        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);

        if(gameCenter.checkAnswer(message)){
            Toast.makeText(LearningMode.this, "Riktig!" + gameCenter.randomNumber, Toast.LENGTH_SHORT).show();


            gameCenter.nextPicture();
            updatePicture();
        }
        else{

            Toast.makeText(LearningMode.this, "Feil! Namnet var " + gameCenter.correctName + gameCenter.randomNumber, Toast.LENGTH_SHORT).show();

            gameCenter.nextPicture();
            updatePicture();
        }

    }

    public void updatePicture(){
        final ImageView imgview = (ImageView) findViewById(R.id.imageView);
        imgview.setImageURI(gameCenter.imgUri);
    }
}
