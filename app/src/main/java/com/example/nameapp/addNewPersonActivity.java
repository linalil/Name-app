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
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.nameapp.HelperClass.addPerson;
import static com.example.nameapp.HelperClass.containsNumber;
import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.isValidString;
import static com.example.nameapp.HelperClass.nameExists;
import static com.example.nameapp.HelperClass.readObject;
import static com.example.nameapp.HelperClass.writeObject;

public class addNewPersonActivity extends AppCompatActivity {


    private static final int SELECT_PICTURE = 1;
    private int PICK_IMAGE_REQUEST = 1;

    private Uri pictureUri;
    private Bitmap bmp;
    private String selectedImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
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

            /*//Må decode Uri til vanleg format utan % og liknande.
            String decodedString = uri.decode(uri.toString());
            Uri decodedUri = Uri.parse(decodedString);
            pictureUri = decodedUri;*/

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


            //selectedImagePath = getRealPathFromURI(this, decodedUri);
            //pictureUri = Uri.parse(selectedImagePath);

            /*
            String generellSti = "content://media/external/images/media/";
            String pathsegment[] = decodedUri.getLastPathSegment().split(":");
            String id = pathsegment[1];
            String generellSti = MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString();
            String total = generellSti + "/" + id;
            pictureUri = Uri.parse(total);
            */

            //String sti = getRealPathFromURI(this, decodedUri);


            /*
            this.grantUriPermission(this.getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            final int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION);
            this.getContentResolver().takePersistableUriPermission(decodedUri, takeFlags);
            */

            //Toast.makeText(addNewPersonActivity.this, "" + decodedString, Toast.LENGTH_LONG).show();

        }

    }

    public void addPersonToApp(View view){

        EditText editText = (EditText) findViewById(R.id.edit_name);
        String name = editText.getText().toString();

        if(!name.isEmpty() && bmp != null && !nameExists(name) && isValidString(name)) {
            addPerson(name, bmp);
            Toast.makeText(addNewPersonActivity.this, "" + name + " was successfully added", Toast.LENGTH_SHORT).show();

            try {
                // Save the list of entries to internal storage
                writeObject(this, "personliste", getListe());

                // Retrieve the list from internal storage
                ArrayList<Person> listFromStorage = (ArrayList<Person>) readObject(this, "personliste");

                // Display the items from the list retrieved.
                Log.d(TAG, "Oppdatert liste er lik: ");

                for (Person entry : listFromStorage) {
                    Log.d(TAG, entry.getName());
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e(TAG, e.getMessage());
            }

            Intent intent;
            intent = new Intent(this, NameList.class);
            startActivity(intent);
        }else {
            if(name.isEmpty()){
                Toast.makeText(addNewPersonActivity.this, "You have to add a name", Toast.LENGTH_LONG).show();
            }
            else if(nameExists(name)){
                Toast.makeText(addNewPersonActivity.this, "Name already exists, pick another", Toast.LENGTH_LONG).show();
            }
            else if(containsNumber(name)){
                Toast.makeText(addNewPersonActivity.this, "Name cannot contain number", Toast.LENGTH_LONG).show();
            }
            else if(!isValidString(name)){
                Toast.makeText(addNewPersonActivity.this, "Illegal character!", Toast.LENGTH_LONG).show();
            }
            else if ( bmp == null) {
                Toast.makeText(addNewPersonActivity.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void cancelAddPersonToApp(View view) {

        Intent intent;
        intent = new Intent(this, NameList.class);
        startActivity(intent);

    }


}
