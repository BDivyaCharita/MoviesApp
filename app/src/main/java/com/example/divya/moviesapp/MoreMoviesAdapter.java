package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 02-02-2017.
 */

public class MoreMoviesAdapter extends RecyclerView.Adapter<MoreMoviesAdapter.MyViewHolder>{

    Bundle extras;
    private Context mContext;
    private ArrayList<Movie> moreMovieList;


    public MoreMoviesAdapter(Context mContext, ArrayList<Movie> moreMovieList) {
        this.mContext = mContext;
        this.moreMovieList = moreMovieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_movie_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoreMoviesAdapter.MyViewHolder holder, int position) {
        Movie movie = moreMovieList.get(position);
        final String url = "https://image.tmdb.org/t/p/w300/"+(movie.getPoster_path());

        holder.moreTitle.setText(movie.getOriginal_title());
        holder.moreYear.setText(movie.getRelease_date());
        holder.poster.setText(movie.getPoster_path());
        holder.language.setText(movie.getOriginal_language());
        holder.overview.setText(movie.getOverview());
        holder.backdrop.setText(movie.getBackdrop_path());
        Glide.with(mContext).load(url).into(holder.morePoster);
    }

    @Override
    public int getItemCount() {
        return moreMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView moreTitle, moreYear;
        public ImageView morePoster;
        protected TextView backdrop;
        protected TextView overview;
        protected TextView poster;
        protected TextView language;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.moreTitle = (TextView) itemView.findViewById(R.id.more_title);
            this.moreYear = (TextView) itemView.findViewById(R.id.more_year);
            this.morePoster= (ImageView) itemView.findViewById(R.id.more_image);
            this.language = (TextView) itemView.findViewById(R.id.language);
            this.overview = (TextView) itemView.findViewById(R.id.overview);
            this.backdrop = (TextView) itemView.findViewById(R.id.backdrop);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),DetailActivity.class);

                    i.putExtra("title", moreTitle.getText());
                    i.putExtra("backdrop", backdrop.getText());
                    i.putExtra("overview", overview.getText());
                    i.putExtra("date", moreYear.getText());
                    i.putExtra("poster", poster.getText());
                    i.putExtra("language", language.getText());

                    v.getContext().startActivity(i);
                    Toast.makeText(v.getContext(), moreTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
