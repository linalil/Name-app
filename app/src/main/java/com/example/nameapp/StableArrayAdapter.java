package com.example.nameapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by theaoen on 17.01.2017.
 */

public class StableArrayAdapter extends ArrayAdapter<String>{


    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}


