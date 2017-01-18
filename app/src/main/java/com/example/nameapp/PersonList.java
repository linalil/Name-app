package com.example.nameapp;

import java.util.ArrayList;

/**
 * Created by theaoen on 18.01.2017.
 */

public class PersonList {

    private static ArrayList<Person> liste;
    private static boolean listInitialized = false;

    public static void initialize(){

        liste = new ArrayList<Person>();

        Person hund1 = new Person("Alpha", R.drawable.sample_0);
        Person hund2 = new Person("Beta", R.drawable.sample_1);

        liste.add(hund1);
        liste.add(hund2);

        listInitialized = true;
    }

    public static ArrayList<Person> getListe(){
        return liste;
    }


    //is initialized
    public static boolean listInitialized(){
        return listInitialized;
    }

    //add person
    public static void addPerson(String name, Integer pictureRef){

        if(!name.isEmpty() || !pictureRef.equals(null)) {
            liste.add(new Person(name, pictureRef));
        }

        else{

            //TODO: Lag feilmelding
            //Skriv ut kva som skjedde feil
        }

    }


}
