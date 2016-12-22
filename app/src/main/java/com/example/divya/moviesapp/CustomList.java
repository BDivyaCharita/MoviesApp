package com.example.divya.moviesapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Divya on 21-12-2016.
 */

public class CustomList extends ArrayAdapter<String>{
    private String[] titles;
    private String[] year;
    private Bitmap[] posters;
    private Activity context;

    public CustomList(Activity context, String[] year, String[] titles, Bitmap[] posters){
        super(context, R.layout.list_view_layout,titles);
        this. context = context;

        this.titles = titles;
        this.year= year;
        this.posters=posters;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);


        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewYear= (TextView) listViewItem.findViewById(R.id.textViewYear);
        ImageView imageViewPoster= (ImageView) listViewItem.findViewById(R.id.imageViewPoster);

        textViewTitle.setText(titles[position]);
        textViewYear.setText(year[position]);
        imageViewPoster.setImageBitmap(posters[position]);

        return listViewItem;
    }



}
