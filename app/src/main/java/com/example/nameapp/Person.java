package com.example.nameapp;

import android.net.Uri;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som skal representere eit Person-objekt.
 * Består av eit namn og ein referanse til biletet av personen.
 */

public class Person {

    String name;

    Integer pictureRef;

    Uri uri;


    public Person(String name, Integer pictureRef){
        this.name = name;
        this.pictureRef = pictureRef;
    }

    public Person(String name, Uri uri){
        this.name = name;
        this.uri = uri;
    }

    void setName(String name){
        this.name = name;
    }

    void setPictureRef(Integer pictureRef){
        this.pictureRef = pictureRef;
    }

    String getName(){
        return name;
    }

    Integer returnPictureRef(){
        return pictureRef;
    }

    void setUri(Uri uri){
        this.uri = uri;
    }

    Uri getUri(){
        return uri;
    }

}
