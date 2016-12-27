package com.example.divya.moviesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Divya on 21-12-2016.
 */

public class CustomList extends ArrayAdapter<String>{
    private String[] titles;
    private String[] year;
    private String[] posters;
    private Activity context;
    ImageView imageViewPoster;

    Context c;

    public CustomList(Activity context, String[] titles , String[] year, String[] posters){
        super(context, R.layout.list_view_layout,titles);
        this. context = context;

        this.titles = titles;
        this.year= year;
        this.posters=posters;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewYear= (TextView) listViewItem.findViewById(R.id.textViewYear);
        ImageView imageViewPoster= (ImageView) listViewItem.findViewById(R.id.imageViewPoster);

        textViewTitle.setText(titles[position]);
        textViewYear.setText(year[position]);
        Glide.with(context).load(posters[position]).placeholder(R.mipmap.ic_launcher).crossFade().into(imageViewPoster);

        return listViewItem;
    }


}
