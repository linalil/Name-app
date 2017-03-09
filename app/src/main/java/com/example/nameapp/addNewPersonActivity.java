package com.example.nameapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.ContentValues.TAG;
import static com.example.nameapp.HelperClass.addPerson;
import static com.example.nameapp.HelperClass.containsNumber;
import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.isValidString;
import static com.example.nameapp.HelperClass.nameExists;
import static com.example.nameapp.HelperClass.writeObject;


/*
* Klasse som tek seg av funksjonalitet for å leggje til ein ny person
* */
public class addNewPersonActivity extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Legg til layouten og skjuler actionbar.
        setContentView(R.layout.activity_add_new_person);
        getSupportActionBar().hide();

    }

    //Metode som køyrer når brukar trykkjer på knapp for å leggje til bilete.
    public void browse_gallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //Metode som køyrer når resultatet returnerast.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            //Prøvar å hente bitmap via MediaStore.
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bmp = bitmap;
            }

            //Fangar opp unntak om noko slår feil.
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void addPersonToApp(View view) {

        //Hentar ut data frå tekstfeltet.
        EditText editText = (EditText) findViewById(R.id.edit_name);
        String name = editText.getText().toString();

        //Sjekkar at namnet oppfyller gitte kriterium før ein legg til ny brukar.
        if (!name.isEmpty() && bmp != null && !nameExists(name) && isValidString(name)) {

            addPerson(name, bmp);
            Toast.makeText(addNewPersonActivity.this, "" + name + " was successfully added", Toast.LENGTH_SHORT).show();


            try {
                // Lagrar lista med personar til persistent lagring.
                writeObject(this, "personliste", getListe());

            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }

            Intent intent;
            intent = new Intent(this, NameList.class);
            startActivity(intent);

        }
        //Dersom noko ikkje stemmer, vil vi skrive ut feilmelding.
        else {
            if (name.isEmpty()) {
                Toast.makeText(addNewPersonActivity.this, "You have to add a name", Toast.LENGTH_LONG).show();
            } else if (nameExists(name)) {
                Toast.makeText(addNewPersonActivity.this, "Name already exists, pick another", Toast.LENGTH_LONG).show();
            } else if (containsNumber(name)) {
                Toast.makeText(addNewPersonActivity.this, "Name cannot contain number", Toast.LENGTH_LONG).show();
            } else if (!isValidString(name)) {
                Toast.makeText(addNewPersonActivity.this, "Illegal character!", Toast.LENGTH_LONG).show();
            } else if (bmp == null) {
                Toast.makeText(addNewPersonActivity.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Metode som går tilbake til namelist ved trykk på cancel.
    public void cancelAddPersonToApp(View view) {

        Intent intent;
        intent = new Intent(this, NameList.class);
        startActivity(intent);

    }


}
