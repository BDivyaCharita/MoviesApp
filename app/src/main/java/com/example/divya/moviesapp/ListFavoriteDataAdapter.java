package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 07-03-2017.
 */

public class ListFavoriteDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListFavoriteDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{

        protected TextView tvTitle;

        protected ImageView itemImage, overflow;

        protected TextView backdrop;
        protected TextView overview;
        protected TextView date;
        protected TextView poster;
        protected TextView language;

    }
    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final LayoutHandler layoutHandler;

        if(row == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_favorite_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.poster = (TextView) row.findViewById(R.id.poster);
            layoutHandler.tvTitle = (TextView) row.findViewById(R.id.tvTitle);
            layoutHandler.date = (TextView) row.findViewById(R.id.date);
            layoutHandler.language = (TextView) row.findViewById(R.id.language);
            layoutHandler.overview = (TextView) row.findViewById(R.id.overview);
            layoutHandler.backdrop = (TextView) row.findViewById(R.id.backdrop);
            layoutHandler.itemImage = (ImageView) row.findViewById(R.id.itemImage);
            row.setTag(layoutHandler);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),DetailActivity.class);

                    i.putExtra("title", layoutHandler.tvTitle.getText());
                    i.putExtra("backdrop", layoutHandler.backdrop.getText());
                    i.putExtra("overview", layoutHandler.overview.getText());
                    i.putExtra("date", layoutHandler.date.getText());
                    i.putExtra("poster", layoutHandler.poster.getText());
                    i.putExtra("language", layoutHandler.language.getText());

                    v.getContext().startActivity(i);
                    Toast.makeText(v.getContext(), layoutHandler.tvTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }
        else{

            layoutHandler = (LayoutHandler) row.getTag();
            }
        Movie movie = (Movie) this.getItem(position);

        final String url= "https://image.tmdb.org/t/p/w300/"+(movie.getPoster_path());

        layoutHandler.poster.setText(movie.getPoster_path());
        layoutHandler.tvTitle.setText(movie.getOriginal_title());
        layoutHandler.date.setText(movie.getRelease_date());
        layoutHandler.language.setText(movie.getOriginal_language());
        layoutHandler.overview.setText(movie.getOverview());
        layoutHandler.backdrop.setText(movie.getBackdrop_path());
        Glide.with(getContext())
                .load(url)
                .centerCrop()
                .into(layoutHandler.itemImage);
        Log.d("url", url);

        return row;
    }
}
