package com.example.nameapp;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som held på og handterar ei liste med ulike personar.
 */

public class PersonList {

    private static ArrayList<Person> liste;
    private static boolean listInitialized = false;

    public static void initialize(Context context){



        liste = new ArrayList<Person>();

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

    //Metode som hentar URI til gitte sample-bilete i drawable.
    public static Uri getUriToDrawable(Context context, int drawableId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );

    }

    public static ArrayList<Person> getListe(){
        return liste;
    }

    public static boolean listInitialized(){
        return listInitialized;
    }

    //add person med Uri
    public static void addPerson(String name, Uri uri){

        if(!name.isEmpty() && !uri.toString().equals(null)) {
            liste.add(new Person(name, uri));
        }

        else{

            //Lag feilmelding

        }

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


    public static Uri findUriFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return p.uri;
            }
        }
        return null;
    }


    public static Bitmap findBitmapFromName(String name){

        for(int i = 0; i < liste.size(); i++){

            Person p = liste.get(i);

            if(p.name.equals(name)){

                return p.bmp;
            }
        }
        return null;
    }


}
