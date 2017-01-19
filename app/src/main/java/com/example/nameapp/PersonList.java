package com.example.nameapp;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

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

        Person hund1 = new Person("Alpha", getUriToDrawable(context, R.drawable.sample_0));
        Person hund2 = new Person("Beta", getUriToDrawable(context, R.drawable.sample_1));
        Person hund3 = new Person("Delta", getUriToDrawable(context, R.drawable.sample_2));

        liste.add(hund1);
        liste.add(hund2);
        liste.add(hund3);

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

    //add person
    public static void addPerson(String name, Uri uri){

        if(!name.isEmpty()) {
            liste.add(new Person(name, uri));
        }

        else{

            //TODO: Lag feilmelding
            //Skriv ut kva som skjedde feil
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



}
