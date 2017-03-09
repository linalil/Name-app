package com.example.nameapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by theaoen on 14.01.2017.
 */

public class ImageAdapter extends ArrayAdapter<Person> {

    //Treng referanse til konteksten
    private Context mContext;

    //Konstruktør
    public ImageAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    //ImageView som eit bilete skal plasserast i.
    private static class ViewHolder {
        private ImageView itemView;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.activity_gallery_imageview, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person item = getItem(position);
        if (item != null) {
            //Set inn biletet i itemView.
            try {
                viewHolder.itemView.setImageBitmap(item.sbmp.bitmap);
            }
            catch(Exception e2){

                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show();

            }

            return convertView;
        }

        return convertView;
    }

}
