package com.example.nameapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.nameapp.PersonList.findUriFromName;
import static com.example.nameapp.PersonList.getListe;
import static com.example.nameapp.PersonList.initialize;
import static com.example.nameapp.PersonList.listInitialized;


//Klassen som listar ut namn
public class NameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        //Brukar klassen ListView for å lage ei liste med namn nedover
        //Finn igjen view som ligg i fila activity_name_list ved å bruke findViewById
        final ListView listview = (ListView) findViewById(R.id.listview);


        if(!listInitialized()){
            initialize(this);
        }
        final ArrayList<Person> liste = getListe();


        //Lagar ein arraylist og fyller inn namna
        final ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < liste.size(); ++i) {
            list.add(liste.get(i).getName());
        }

        //Brukar ein eigendefinert adapter for å fikse layouten på lista
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
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

                //Finn Uri til biletet, ved å bruke statisk metode frå PersonList-klassen + namnet.
                Uri imgUri = findUriFromName(person);

                //Lagar dialog v.h.a metode og Uri.
                showImage(imgUri);

            }

        });

    }

    //Metode som lagar pop-up vindauge med bilete gitt som Uri.
    public void showImage(Uri imageUri) {
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

        ImageView imageView = new ImageView(this);
        imageView.setImageURI(imageUri);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }





}

