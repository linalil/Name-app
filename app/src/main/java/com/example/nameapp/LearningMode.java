package com.example.nameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LearningMode extends AppCompatActivity {

    GameCenter gameCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        getSupportActionBar().hide();


        //Initialiserar gameCenter-objektet for spel-logikk.
        gameCenter = new GameCenter(this);
        gameCenter.nextPicture();
        updatePicture();
        updateScore();

    }

    //Metode som køyrast når brukar klikkar på svar-knappen.
    public void sendAnswer(View view) {
        //Hentar inn svaret frå brukaren og nullar ut tekstfeltet
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        editText.setText("");

        //Brukar spel-klassen til å sjekke svar og lage klart nytt bilete.
        gameCenter.checkAnswer(message);
        gameCenter.nextPicture();

        //Oppdaterar view for bilete og tekst.
        updatePicture();
        updateScore();
    }

    //Metode som legg inn bilete i imageView
    public void updatePicture(){
        final ImageView imgview = (ImageView) findViewById(R.id.imageView);
        imgview.setImageBitmap(gameCenter.bmp);
    }

    //Metode som oppdaterar tekstfelt med score og tal forsøk.
    public void updateScore(){

        String numCorrect = "Score: " + gameCenter.score;
        String numAttempts = "Attempts: " + gameCenter.attempts;

        TextView score = (TextView) findViewById(R.id.score);
        score.setText(numCorrect);

        TextView attempts = (TextView) findViewById(R.id.attempts);
        attempts.setText(numAttempts);

    }

    //Metode som køyrer når brukar går tilbake og ut av aktiviteten.
    public void goBack(View view) {

        Toast.makeText(LearningMode.this, "Scored " + gameCenter.score + " of " + gameCenter.attempts , Toast.LENGTH_SHORT).show();

        Intent intent;
        intent = new Intent(this, MainMenu.class);
        startActivity(intent);

    }
}
