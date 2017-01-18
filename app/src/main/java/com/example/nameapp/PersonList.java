package com.example.nameapp;

import java.util.ArrayList;

/**
 * Created by theaoen on 18.01.2017.
 */

public class PersonList {

    private static ArrayList<Person> liste;

    public static void initialize(){

        liste = new ArrayList<Person>();

        Person hund1 = new Person("Alpha", R.drawable.sample_0);
        Person hund2 = new Person("Beta", R.drawable.sample_1);

        liste.add(hund1);
        liste.add(hund2);
    }

    public static ArrayList<Person> getListe(){
        return liste;
    }

    //is initialized

    //add person

    //Variabel for å sjekke om initialisert



}
