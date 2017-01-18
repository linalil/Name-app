package com.example.nameapp;

/**
 * Created by theaoen on 18.01.2017.
 */

public class Person {

    String name;

    Integer pictureRef;


    public Person(String name, Integer pictureRef){
        this.name = name;
        this.pictureRef = pictureRef;
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


}
