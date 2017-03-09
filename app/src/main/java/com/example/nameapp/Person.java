package com.example.nameapp;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by theaoen on 18.01.2017.
 * Klasse som skal representere eit Person-objekt.
 * Består av eit namn og ein SerialBitmap med biletet av personen.
 */

public class Person implements Serializable {

    String name;
    SerialBitmap sbmp;

    public Person(String name, Bitmap bmp) {
        this.name = name;
//        this.bmp = bmp;
        this.sbmp = new SerialBitmap(bmp);
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

}
