package com.example.nameapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.nameapp.PersonList.addPerson;

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

    /*public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {

            String wholeID = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                wholeID = DocumentsContract.getDocumentId(contentUri);
            }
            String id = wholeID.split(":")[1];
            String[] column = { MediaStore.Images.Media.DATA };
            String sel = MediaStore.Images.Media._ID + "=?";

            cursor = getContentResolver().
                    query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, new String[]{ id }, null);

            String filePath = "";
            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }

            cursor.close();
            return  filePath;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }*/

    public void addPersonToApp(View view){

        EditText editText = (EditText) findViewById(R.id.edit_name);
        String name = editText.getText().toString();

        if(!name.isEmpty() && bmp != null) {
            addPerson(name, bmp);
            Toast.makeText(addNewPersonActivity.this, "" + name + " was successfully added", Toast.LENGTH_SHORT).show();

            finish();
        }else {
            if(name.isEmpty()){
                Toast.makeText(addNewPersonActivity.this, "You have to add a name", Toast.LENGTH_LONG).show();
            }
            else if ( bmp == null) {
                Toast.makeText(addNewPersonActivity.this, "" + name + ", you have to add a picture of yourself!", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void cancelAddPersonToApp(View view) {

        finish();

    }


}
