package com.example.nameapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.nameapp.PersonList.addPerson;

public class addNewPersonActivity extends AppCompatActivity {


    private static final int SELECT_PICTURE = 1;
    private int PICK_IMAGE_REQUEST = 1;

    private Uri pictureUri;

    private String selectedImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);

        /*
        findViewById(R.id.browse_gallery)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });

        */



    }

    public void browse_gallery(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            pictureUri = uri;

            Toast.makeText(addNewPersonActivity.this, "" +  pictureUri, Toast.LENGTH_LONG).show();

        }
    }


    public void addPersonToApp(View view){

        EditText editText = (EditText) findViewById(R.id.edit_name);
        String name = editText.getText().toString();

        addPerson(name, pictureUri);
        Toast.makeText(addNewPersonActivity.this, "" +  name + " was successfully added", Toast.LENGTH_SHORT).show();

        finish();
    }
/*
    public void showChosenPicture(int requestCode, int resultCode, Intent data){
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            pictureUri = selectedImageUri;
            ImageView imageView = (ImageView) findViewById(R.id.addedPictureView);
            imageView.setImageURI(null);
            imageView.setImageURI(pictureUri);
        }
    }
*/
    public void cancelAddPersonToApp(View view) {

        finish();

    }


}
