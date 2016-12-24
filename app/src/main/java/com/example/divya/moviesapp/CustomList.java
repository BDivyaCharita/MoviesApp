package com.example.divya.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Divya on 21-12-2016.
 */

public class CustomList extends ArrayAdapter<String>{
    private String[] titles;
    private String[] year;

    private Activity context;

    Context c;

    public CustomList(Activity context, String[] titles , String[] year){
        super(context, R.layout.list_view_layout,titles);
        this. context = context;

        this.titles = titles;
        this.year= year;



    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);


        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewYear= (TextView) listViewItem.findViewById(R.id.textViewYear);


        textViewTitle.setText(titles[position]);
        textViewYear.setText(year[position]);
        return listViewItem;
    }






    }

