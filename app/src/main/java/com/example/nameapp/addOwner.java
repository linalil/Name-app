package com.example.nameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.nameapp.HelperClass.containsNumber;
import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.isValidString;
import static com.example.nameapp.HelperClass.nameExists;
import static com.example.nameapp.HelperClass.readObject;
import static com.example.nameapp.HelperClass.writeObject;

public class addOwner extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;
    public static final String PREFS_NAME = "MyPrefsFile";

    private Bitmap bmp;
    private SerialBitmap serialBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_owner);
        getSupportActionBar().hide();
    }

    public void browse_gallery(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bmp = bitmap;
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public void addPersonToApp(View view){

        EditText editText = (EditText) findViewById(R.id.edit_owner);
        String name = editText.getText().toString();

        if(!name.isEmpty() && bmp != null && !containsNumber(name) && isValidString(name)) {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("owner", name);
            editor.commit();

            //TODO: Lagre en eller anna versjon av biletet i internt minne.

            serialBitmap = new SerialBitmap(bmp);

            try {
                // Save the list of entries to internal storage
                writeObject(this, "ownerimage", serialBitmap);

            } catch (IOException e) {
                Log.e(TAG, "IOException adding ownerpicture");
            }

            Toast.makeText(addOwner.this, "" + name + " was successfully added as owner of app", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);

            finish();
        }else {
            if(name.isEmpty()){
                Toast.makeText(addOwner.this, "You have to add a name", Toast.LENGTH_LONG).show();
            }
            else if(containsNumber(name)){
                Toast.makeText(addOwner.this, "Name cannot contain number", Toast.LENGTH_LONG).show();
            }
            else if(!isValidString(name)){
                Toast.makeText(addOwner.this, "Illegal character!", Toast.LENGTH_LONG).show();
            }
            else if ( bmp == null) {
                Toast.makeText(addOwner.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }

}
