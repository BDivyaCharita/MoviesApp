package com.example.divya.moviesapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 02-02-2017.
 */

public class MoreMoviesAdapter extends RecyclerView.Adapter<MoreMoviesAdapter.MyViewHolder>{

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

        Glide.with(mContext).load(url).into(holder.morePoster);
    }

    @Override
    public int getItemCount() {
        return moreMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView moreTitle, moreYear;
        public ImageView morePoster;

        public MyViewHolder(View itemView) {
            super(itemView);
            moreTitle = (TextView) itemView.findViewById(R.id.more_title);
            moreYear = (TextView) itemView.findViewById(R.id.more_year);
            morePoster= (ImageView) itemView.findViewById(R.id.more_image);

        }
    }
}
