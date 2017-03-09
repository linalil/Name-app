package com.example.nameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        //Initialiserar ei tom liste med personar.
        liste = new ArrayList<Person>();

        //Legg til dei tre gruppemedlemma som objekt i denne.
        Person thea = new Person("Thea", BitmapFactory.decodeResource(context.getResources(), R.drawable.thea_bw));
        Person marita = new Person("Marita", BitmapFactory.decodeResource(context.getResources(), R.drawable.marita_bw));
        Person lina = new Person("Lina", BitmapFactory.decodeResource(context.getResources(), R.drawable.lina_bw));

        liste.add(thea);
        liste.add(marita);
        liste.add(lina);

        //Hentar inn eigaren frå minnet via getOwnerFromMemory-metoden i klassen.
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


        //Dersom det fins fleire personar i internminne, legg desse til i lista.
        if(listFromInternalMemory != null){

            for(int i = 0; i < listFromInternalMemory.size(); i++){
                Person p = listFromInternalMemory.get(i);

                //Namnet kan ikkje fins frå før.
                if(!nameExists(p.name)){
                    liste.add(p);
                    Log.d(TAG, "Lagt til ein ny person " + p.name);
                }
            }
        }
        listInitialized = true;
    }

    //Metode som returnerar personlista.
    public static ArrayList<Person> getListe(){
        return liste;
    }

    //Metode som sjekkar at namnet ikkje fins i lista frå før.
    public static boolean nameExists(String name){

        for(int i = 0; i < liste.size(); i++){
            Person p = liste.get(i);
            if(p.name.equals(name)){

                return true;
            }
        }
        return false;
    }

    //Returnerar boolsk variabel som fortel om lista er initialisert.
    public static boolean listInitialized(){
        return listInitialized;
    }

    //add person med bitmap
    public static void addPerson(String name, Bitmap bmp){

        if(!name.isEmpty()) {
            liste.add(new Person(name, bmp));
        }

        else{

            //Lag feilmelding...

        }

    }

    //metode som hentar finn biletet til ein person basert på namn.
    public static Bitmap findBitmapFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return p.sbmp.bitmap;
            }
        }
        return null;
    }

    //Metode som skriv objekt til fil.
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    //Metode som returnerar objekt frå fil.
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }


    //Metode som returnerar eit Person-objekt med eigar sine data
    public static Person getOwnerFromMemory(Context context){
        Person p = null;

        //Hentar inn SharedPreferences lagra under SplashScreen.
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

        //Dersom biletet fins og eigar sitt namn er i minnet..
        if(serialBitmap != null && settings.contains("owner")){

            Bitmap bmp = serialBitmap.bitmap;
            String name = settings.getString("owner", "");

            //..opprett personobjekt med gitte data.
            p = new Person(name, bmp);

        }

        return p;
    }


    //Metode som validerar om String inneheld tal.
    public static boolean containsNumber(String name){
        if(name.matches(".*\\d.*")){
            return true;
        } else{
            return false;
        }
    }

    //Metode som validerar om String er gyldig.
    public static boolean isValidString(String name){
        return name.matches("[-a-zA-ZæøåÆØÅ^0-9 ]+");

    }



}
