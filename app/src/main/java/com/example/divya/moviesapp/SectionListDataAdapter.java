package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Divya on 12-01-2017.
 */

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<Movie> itemsList;
    private Context mContext;

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage, overflow;

        protected TextView backdrop;
        protected TextView overview;
        protected TextView date;
        protected TextView poster;
        protected TextView language;

        public SingleItemRowHolder(View view) {
            super(view);
            this.poster = (TextView)view.findViewById(R.id.poster);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.date = (TextView) view.findViewById(R.id.date);
            this.language = (TextView) view.findViewById(R.id.language);
            this.overview = (TextView) view.findViewById(R.id.overview);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            this.backdrop = (TextView) view.findViewById(R.id.backdrop);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);


            view.setOnClickListener(new View.OnClickListener() {
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


    public SectionListDataAdapter(Context context, ArrayList<Movie> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, int i) {
        Movie singleItem = itemsList.get(i);
        final String url= "https://image.tmdb.org/t/p/w300/"+(singleItem.getPoster_path());

        holder.poster.setText(singleItem.getPoster_path());
        holder.tvTitle.setText(singleItem.getOriginal_title());
        holder.date.setText(singleItem.getRelease_date());
        holder.language.setText(singleItem.getOriginal_language());
        holder.overview.setText(singleItem.getOverview());
        holder.backdrop.setText(singleItem.getBackdrop_path());
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.itemImage);
        Log.d("url", url);

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
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
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
        return (null != itemsList ? itemsList.size() : 0);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}