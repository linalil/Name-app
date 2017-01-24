package com.example.nameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.nameapp.PersonList.addPerson;
import static com.example.nameapp.PersonList.nameExists;

public class addOwner extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private int PICK_IMAGE_REQUEST = 1;
    public static final String PREFS_NAME = "MyPrefsFile";


    private Uri pictureUri;
    private Bitmap bmp;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_owner);
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

        EditText editText = (EditText) findViewById(R.id.edit_name);
        String name = editText.getText().toString();

        if(!name.isEmpty() && bmp != null && !nameExists(name)) {


            //TODO: Lagre namn til personen som "owner" i SharedPreferences

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("owner", name);

            editor.commit();


            //TODO: Lagre en eller anna versjon av biletet i internt minne.



            //TODO: Lag en intent som tar personen til hovudmeny når owner er lagt til.
            //"Opne" denne om personen skal bli med i spel/oversikt osv.
            //addPerson(name, bmp);

            Toast.makeText(addOwner.this, "" + name + " was successfully added as owner of app", Toast.LENGTH_SHORT).show();

            finish();
        }else {
            if(name.isEmpty()){
                Toast.makeText(addOwner.this, "You have to add a name", Toast.LENGTH_LONG).show();
            }
            else if(nameExists(name)){
                Toast.makeText(addOwner.this, "Name already exists, pick another", Toast.LENGTH_LONG).show();
            }
            else if ( bmp == null) {
                Toast.makeText(addOwner.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void cancelAddPersonToApp(View view) {

        finish();

    }




}
