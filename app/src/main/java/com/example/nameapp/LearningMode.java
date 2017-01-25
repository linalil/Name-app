package com.example.nameapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.FileNotFoundException;
import java.io.InputStream;

public class LearningMode extends AppCompatActivity {

    GameCenter gameCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        getSupportActionBar().hide();

        gameCenter = new GameCenter(this);
        gameCenter.nextPicture();
        updatePicture();
        updateScore();

    }

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

        /*
        InputStream input;
        Bitmap bmp;
        try {
            input = this.getContentResolver().openInputStream(gameCenter.imgUri);
            bmp = BitmapFactory.decodeStream(input);
            imgview.setImageBitmap(bmp);
        } catch (FileNotFoundException e1) {

            Toast.makeText(this, "Could not find image", Toast.LENGTH_LONG).show();

        }
        catch(Exception e2){

            Toast.makeText(this, "Picture not viewable", Toast.LENGTH_LONG).show();

        }*/

        imgview.setImageBitmap(gameCenter.bmp);
        //imgview.setImageURI(gameCenter.imgUri);
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
    public void goBack(View view) {

        Toast.makeText(LearningMode.this, "Scored " + gameCenter.score + " of " + gameCenter.attempts , Toast.LENGTH_SHORT).show();

        finish();

    }
}
