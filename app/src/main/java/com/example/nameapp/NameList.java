package com.example.nameapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
            initialize();
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


                //Foreløpig skjer det ein animasjon som fjernar listeelement,
                //men ein skal her fikse slik at det poppar opp eit bilete.
                final Person person = (Person) parent.getItemAtPosition(position);

                //TODO: Make dialog....

            }

        });

    }
}
