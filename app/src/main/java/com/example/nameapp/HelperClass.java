package com.example.nameapp;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
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
import static com.example.nameapp.SplashScreen.PREFS_NAME;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som held på og handterar ei liste med ulike personar.
 */

public class HelperClass {

    private static ArrayList<Person> liste;
    private static boolean listInitialized = false;

    public static void initialize(Context context){

        liste = new ArrayList<Person>();

        Person thea = new Person("Thea", BitmapFactory.decodeResource(context.getResources(), R.drawable.thea_bw));
        Person marita = new Person("Marita", BitmapFactory.decodeResource(context.getResources(), R.drawable.marita_bw));
        Person lina = new Person("Lina", BitmapFactory.decodeResource(context.getResources(), R.drawable.lina_bw));

        liste.add(thea);
        liste.add(marita);
        liste.add(lina);

        Person owner = getOwnerFromMemory(context);
        if(owner != null){
            liste.add(owner);
        }
        else{

            Log.d(TAG, "Det gjekk ikkje å hente eigaren");
        }


        //Hentar lista med alle frå minnet.
        ArrayList<Person> listFromInternalMemory = null;
        try {
            // Retrieve the list from internal storage
            listFromInternalMemory = (ArrayList<Person>) readObject(context, "personliste");

            // Display the items from the list retrieved.
            for (Person entry : listFromInternalMemory) {
                Log.d(TAG, entry.getName());
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        if(listFromInternalMemory != null){

            for(int i = 0; i < listFromInternalMemory.size(); i++){

                Person p = listFromInternalMemory.get(i);

                if(!nameExists(p.name)){

                    liste.add(p);
                    Log.d(TAG, "Lagt til ein ny person " + p.name);
                }
            }
        }
        listInitialized = true;
    }

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

    //add person med bitmap
    public static void addPerson(String name, Bitmap bmp){

        if(!name.isEmpty()) {
            liste.add(new Person(name, bmp));
        }

        else{

            //Lag feilmelding

        }

    }

    public static Bitmap findBitmapFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return p.sbmp.bitmap;
            }
        }
        return null;
    }

    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }


    public static Person getOwnerFromMemory(Context context){

        Person p = null;

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);

        //Hentar biletet til eigaren frå minnet.
        SerialBitmap serialBitmap = null;
        try {
            serialBitmap = (SerialBitmap) readObject(context, "ownerimage");
        }
        catch (IOException e){
            Log.d(TAG, "IOException");
        }
        catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        if(serialBitmap != null && settings.contains("owner")){

            Bitmap bmp = serialBitmap.bitmap;
            String name = settings.getString("owner", "");

            p = new Person(name, bmp);

        }

        return p;
    }

}
