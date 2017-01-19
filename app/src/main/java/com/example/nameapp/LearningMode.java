package com.example.nameapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LearningMode extends AppCompatActivity {

    GameCenter gameCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);



        gameCenter = new GameCenter(this);
        gameCenter.nextPicture();

        updatePicture();
        updateScore();

    }

    public void sendAnswer(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        editText.setText("");

        gameCenter.checkAnswer(message);
        gameCenter.nextPicture();
        updatePicture();
        updateScore();

    }

    public void updatePicture(){
        final ImageView imgview = (ImageView) findViewById(R.id.imageView);
        imgview.setImageURI(gameCenter.imgUri);
    }

    public void updateScore(){

        String numCorrect = "Score: " + gameCenter.score;
        String numAttempts = "Attempts: " + gameCenter.attempts;

        TextView score = (TextView) findViewById(R.id.score);
        score.setText(numCorrect);

        TextView attempts = (TextView) findViewById(R.id.attempts);
        attempts.setText(numAttempts);

    }
}
