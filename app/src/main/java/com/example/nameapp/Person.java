package com.example.nameapp;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som skal representere eit Person-objekt.
 * Består av eit namn og ein referanse til biletet av personen.
 */

public class Person {

    String name;

    Uri uri;

    Bitmap bmp;



    public Person(String name, Uri uri){
        this.name = name;
        this.uri = uri;
    }

    public Person(String name, Bitmap bmp){
        this.name = name;
        this.bmp = bmp;
    }

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    void setUri(Uri uri){
        this.uri = uri;
    }

    Uri getUri(){
        return uri;
    }

    void setBmp(Bitmap bmp){
        this.bmp = bmp;
    }

    Bitmap getBmp(){
        return bmp;
    }


}
