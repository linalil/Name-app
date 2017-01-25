package com.example.nameapp;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som held på og handterar ei liste med ulike personar.
 */

public class PersonList {

    private static ArrayList<Person> liste;
    private static boolean listInitialized = false;

    private static Context c;

    private static final String FILEPATH = "";

    public static void initialize(Context context){

        liste = new ArrayList<Person>();

        c = context;

        /* Versjon med URI
        Person thea = new Person("Thea", getUriToDrawable(context, R.drawable.thea_bw));
        Person marita = new Person("Marita", getUriToDrawable(context, R.drawable.marita_bw));
        Person lina = new Person("Lina", getUriToDrawable(context, R.drawable.lina_bw));
        */

        Person thea = new Person("Thea", BitmapFactory.decodeResource(context.getResources(), R.drawable.thea_bw));
        Person marita = new Person("Marita", BitmapFactory.decodeResource(context.getResources(), R.drawable.marita_bw));
        Person lina = new Person("Lina", BitmapFactory.decodeResource(context.getResources(), R.drawable.lina_bw));

        liste.add(thea);
        liste.add(marita);
        liste.add(lina);

        listInitialized = true;
    }

    /*//Metode som hentar URI til gitte sample-bilete i drawable.
    public static Uri getUriToDrawable(Context context, int drawableId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );

    }*/

    public static ArrayList<Person> getListe(){
        return liste;
    }


    public static boolean nameExists(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return true;
            }
        }
        return false;
    }

    public static boolean listInitialized(){
        return listInitialized;
    }

  /*  //add person med Uri
    public static void addPerson(String name, Uri uri){

        if(!name.isEmpty() && !uri.toString().equals(null)) {
            liste.add(new Person(name, uri));
        }

        else{

            //Lag feilmelding

        }

    }*/

    //add person med bitmap
    public static void addPerson(String name, Bitmap bmp){

        if(!name.isEmpty()) {
            liste.add(new Person(name, bmp));
        }

        else{

            //Lag feilmelding

        }

    }


    /*public static Uri findUriFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name) && p.sbmp.bitmap != null ){

                return p.uri;
            }
        }
        return null;
    }
*/

    public static Bitmap findBitmapFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return p.sbmp.bitmap;
            }
        }
        return null;
    }

    public static void saveFile(Context context, ArrayList<Person> person, String filename) throws IOException {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            byte[] p = convertToBytes(person);
            fos.write(p);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("file", "file not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("file", "io exception");
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> loadFile(Context context, String filename) throws IOException {
        BufferedReader input = null;
        FileInputStream in = new FileInputStream(new File(c.getCacheDir(), filename));

        try {
            input = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuffer buffer = new StringBuffer();

            while((line = input.readLine()) != null){
                buffer.append(line);
            }

            byte[] array = String.valueOf(buffer).getBytes();

            try {
                ArrayList<Person> liste = (ArrayList<Person>) convertToPersonlist(array);
                return liste;
            }
            catch (ClassNotFoundException e){
                Log.d("Class", "ClassNotFoundException");
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            Log.d("file", "file not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("file", "io exception");
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] convertToBytes(ArrayList<Person> p) throws IOException{
        try{

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(p);
            return out.toByteArray();
        }
        catch (IOException e){
            Log.d(TAG, "IOException");

        }
        return null;
    }

    public static ArrayList<Person> convertToPersonlist(byte[] data) throws IOException, ClassNotFoundException {

        try{

            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return (ArrayList<Person>) is.readObject();
        }
        catch(IOException e){
            Log.d(TAG, "IOException");
        }

        return null;
    }


}
