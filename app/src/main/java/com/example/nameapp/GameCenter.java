package com.example.nameapp;

import android.content.Context;
import android.net.Uri;

import com.example.nameapp.Person;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.nameapp.PersonList.getListe;
import static com.example.nameapp.PersonList.initialize;
import static com.example.nameapp.PersonList.listInitialized;

/**
 * Created by theaoen on 19.01.2017.
 */

public class GameCenter {

    Context context;
    Integer score;
    Integer attempts;
    Integer randomNumber;
    Person p;
    String correctName;
    Uri imgUri;
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


        //Hentar eit nytt bilete, og finn namn og Uri.
        p = liste.get(randomNumber);
        correctName = p.name;
        imgUri = p.uri;

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
