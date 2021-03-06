package com.example.nameapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.nameapp.HelperClass.findBitmapFromName;
import static com.example.nameapp.HelperClass.getListe;
import static com.example.nameapp.HelperClass.initialize;
import static com.example.nameapp.HelperClass.listInitialized;


//Klassen som listar ut namn
public class NameList extends AppCompatActivity {


    private StableArrayAdapter adapter;
    private ArrayList<Person> liste;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
        getSupportActionBar().hide();

        //Brukar ListView for å lage ei liste med namn nedover
        listview = (ListView) findViewById(R.id.listview);

        //Sjekkar om lista er initialisert, og hentar ein instanse av lista.
        if(!listInitialized()) {
            initialize(this);
        }
        liste = getListe();


        //Lagar ein arraylist og fyller inn namna
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < liste.size(); ++i) {
            list.add(liste.get(i).getName());
        }

        //Brukar ein eigendefinert adapter for å fikse layouten på lista
       adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        //Set ein lyttar som fortel kva som skal skje når vi trykker på eit element i lista.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                //Finn ut kva namn som blei trykt på.
                final String person = (String) parent.getItemAtPosition(position);

                Bitmap bmp = findBitmapFromName(person);

                //Lagar dialog v.h.a metode og bitmap.
                try {
                    showImage(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }


    @Override
    public void onResume() {

        if(!listInitialized()){
            initialize(this);
       }
        liste = getListe();

        final ArrayList<String> list = new ArrayList<String>();

        listview = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < liste.size(); ++i) {
            list.add(liste.get(i).getName());
        }

        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        super.onResume();
    }

    //Metode som lagar pop-up vindauge med bilete gitt som bitmap.
    public void showImage(Bitmap bmp) throws IOException {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        //Lagar ny imageview og legg inn biletet i den.
        ImageView imageView = new ImageView(this);
        try {
            imageView.setImageBitmap(bmp);
        }

        catch(Exception e2){
            String e = e2.getLocalizedMessage();
            String e3 = e2.getMessage();
            String to = e2.toString();

            System.out.println("GetLocalizedMessage returnerar: " + e);
            System.out.println("GetMessage returnerar: " + e3);
            System.out.println("toString returnerar: " + to);
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        //Legg til biletet til dialogen.
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    //Metode som køyrer når ein trykkjer på addNewPerson-knapp.
    public void addNewPerson(View view){
        Intent intent;
        intent = new Intent(this, addNewPersonActivity.class);
        startActivity(intent);
    }

    //Metode som køyrer når ein trykkjer på back.
    public void goBack(View view) {
        Intent intent;
        intent = new Intent(this, MainMenu.class);
        startActivity(intent);

    }


}

