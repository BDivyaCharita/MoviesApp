package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.divya.moviesapp.Model.TvShow;

import java.util.ArrayList;

/**
 * Created by Divya on 15-02-2017.
 */

public class MoreTvAdapter extends RecyclerView.Adapter<MoreTvAdapter.MyViewHolder>{

    Bundle extras;
    private Context mContext;
    private ArrayList<TvShow> itemList;


    public MoreTvAdapter(Context mContext, ArrayList<TvShow> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public MoreTvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_movie_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MoreTvAdapter.MyViewHolder holder, int position) {
        TvShow movie = itemList.get(position);
        final String url = "https://image.tmdb.org/t/p/w300/"+(movie.getPoster_path());

        holder.tvTitle.setText(movie.getOriginal_name());
        holder.date.setText(movie.getFirst_air_date());
        holder.poster.setText(movie.getPoster_path());
        holder.language.setText(movie.getOriginal_language());
        holder.overview.setText(movie.getOverview());
        holder.backdrop.setText(movie.getBackdrop_path());
        Glide.with(mContext).load(url).into(holder.itemImage);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_movie, popup.getMenu());
        popup.setOnMenuItemClickListener(new MoreTvAdapter.MyMenuItemClickListener());
        popup.show();
    }


    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;

                default:
            }
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, date;
        public ImageView itemImage,overflow;
        protected TextView backdrop;
        protected TextView overview;
        protected TextView poster;
        protected TextView language;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.poster = (TextView)itemView.findViewById(R.id.poster);
            this.tvTitle = (TextView) itemView.findViewById(R.id.more_title);
            this.date = (TextView) itemView.findViewById(R.id.more_year);
            this.itemImage= (ImageView) itemView.findViewById(R.id.more_image);
            this.language = (TextView) itemView.findViewById(R.id.language);
            this.overview = (TextView) itemView.findViewById(R.id.overview);
            this.backdrop = (TextView) itemView.findViewById(R.id.backdrop);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),DetailActivity.class);

                    i.putExtra("title", tvTitle.getText());
                    i.putExtra("backdrop", backdrop.getText());
                    i.putExtra("overview", overview.getText());
                    i.putExtra("date", date.getText());
                    i.putExtra("poster", poster.getText());
                    i.putExtra("language", language.getText());

                    v.getContext().startActivity(i);
                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}

