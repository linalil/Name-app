package com.example.nameapp;

import android.content.Intent;
import android.content.SharedPreferences;
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
import static com.example.nameapp.HelperClass.containsNumber;
import static com.example.nameapp.HelperClass.isValidString;
import static com.example.nameapp.HelperClass.writeObject;

public class addOwner extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    public static final String PREFS_NAME = "MyPrefsFile";

    private Bitmap bmp;
    private SerialBitmap serialBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Legg til layout og skjuler actionbar.
        setContentView(R.layout.activity_add_owner);
        getSupportActionBar().hide();
    }

    //Metode som køyrer når brukar vil opne galleriet.
    public void browse_gallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //Metode som handterar resultat av browse_gallery.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //sjekkar at alt har gått som forventa.
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            //Prøvar å hente bitmap via Uri og MediaStore.
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bmp = bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //Metode som legg til eigaren i appen.
    public void addPersonToApp(View view) {

        //Hentar inn namn frå tekstfelt.
        EditText editText = (EditText) findViewById(R.id.edit_owner);
        String name = editText.getText().toString();

        //Sjekkar at ein har eit bilete og at namnet er gyldig.
        if (!name.isEmpty() && bmp != null && isValidString(name)) {

            //Lagrar eigaren sitt namn i SharedPreferences.
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("owner", name);
            editor.commit();

            //Prøvar å hente inn lagra bilete frå internminne via SerialBitmap.
            serialBitmap = new SerialBitmap(bmp);
            try {
                // Lagrar lista i internt minne.
                writeObject(this, "ownerimage", serialBitmap);

            } catch (IOException e) {
                Log.e(TAG, "IOException adding ownerpicture");
            }

            Toast.makeText(addOwner.this, "" + name + " was successfully added as owner of app", Toast.LENGTH_SHORT).show();

            //Går vidare til hovudmeny.
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);

            finish();


        }
        //Dersom noko skjedde feil med namnet, skriv feilmelding.
        else {
            if (name.isEmpty()) {
                Toast.makeText(addOwner.this, "You have to add a name", Toast.LENGTH_LONG).show();
            } else if (containsNumber(name)) {
                Toast.makeText(addOwner.this, "Name cannot contain number", Toast.LENGTH_LONG).show();
            } else if (!isValidString(name)) {
                Toast.makeText(addOwner.this, "Illegal character!", Toast.LENGTH_LONG).show();
            } else if (bmp == null) {
                Toast.makeText(addOwner.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
