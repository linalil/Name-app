package com.example.nameapp;

import java.util.ArrayList;

/**
 * Created by theaoen on 18.01.2017.
 */

public class PersonList {

    ArrayList<Person> liste;

    public PersonList(){

        liste = new ArrayList<Person>();

        Person hund1 = new Person("Alpha", R.drawable.sample_0);
        Person hund2 = new Person("Beta", R.drawable.sample_1);

        liste.add(hund1);
        liste.add(hund2);

    }


    ArrayList<Person> getListe(){
        return liste;
    }



}
