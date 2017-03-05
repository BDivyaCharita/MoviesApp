package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 02-02-2017.
 */

public class MoreMoviesAdapter extends RecyclerView.Adapter<MoreMoviesAdapter.MyViewHolder>{

    Bundle extras;
    private Context mContext;
    private ArrayList<Movie> itemList;


   /* public MoreMoviesAdapter(String[] poster_path, String[] overview, String[] release_date, String[] original_title, String[] original_language, String[] backdrop_path, String[] url) {
        super();
        itemList = new ArrayList<Movie>();
        for(int i=0; i<poster_path.length; i++){
            Movie item = new Movie();
            item.setPoster_path(poster_path[i]);
            item.setOverview(overview[i]);
            item.setRelease_date(release_date[i]);
            item.setOriginal_title(original_title[i]);
            item.setOriginal_language(original_language[i]);
            item.setBackdrop_path(backdrop_path[i]);
            item.setUrl(url[i]);
            itemList.add(item);
        }
    }
    */
    public MoreMoviesAdapter(Context mContext, ArrayList<Movie> itemList){
        this.mContext=mContext;
        this.itemList=itemList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_movie_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MoreMoviesAdapter.MyViewHolder holder, int position) {
        Movie movie = itemList.get(position);
        final String url = "https://image.tmdb.org/t/p/w300/"+(movie.getPoster_path());

        holder.tvTitle.setText(movie.getOriginal_title());
        holder.date.setText(movie.getRelease_date());
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
        popup.setOnMenuItemClickListener(new MoreMoviesAdapter.MyMenuItemClickListener());
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
