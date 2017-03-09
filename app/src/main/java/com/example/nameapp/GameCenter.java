package com.example.nameapp;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.initialize;
import static com.example.nameapp.HelperClass.listInitialized;

/**
 * Created by theaoen on 19.01.2017.
 * Klasse som styrer logikken bak LearningMode.
 */

public class GameCenter {

    Context context;
    Integer score;
    Integer attempts;
    Integer randomNumber;
    Person p;
    String correctName;
    Bitmap bmp;
    final ArrayList<Person> liste;

    //Konstruktør
    public GameCenter(Context c){
        context = c;
        score = 0;
        attempts = 0;

        if(!listInitialized()){
            initialize(context);
        }

        liste = getListe();

    }

    //Hentar inn neste bilete
    public void nextPicture(){
        //Lagar tilfeldig tal for å hente eit gitt bilete
        Random rand = new Random(System.currentTimeMillis());
        randomNumber = rand.nextInt(liste.size());

        //Plukkar ein tilfeldig person, og setter namn + bilete for denne.
        p = liste.get(randomNumber);
        correctName = p.name;
        bmp = p.sbmp.bitmap;

    }

    //Sjekkar om svaret var riktig
    public void checkAnswer(String name){
        if(correctName.toLowerCase().equals(name.toLowerCase())){
            attempts++;
            score++;
        }
        else{
            attempts++;
        }
    }


    //Metode som genererar tilfeldig tal i intervall
    private Integer createRandom(Integer min, Integer max){
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }




}
