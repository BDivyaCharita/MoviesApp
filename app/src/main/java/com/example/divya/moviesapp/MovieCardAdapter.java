package com.example.divya.moviesapp;

import android.app.Activity;
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


public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {



    private List<Movie> mMovieList;
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    private String[] mTitles;
    private String[] mImagesPopular;
    private Activity context;

  /*  public MovieCardAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }
    */

    @Override
    public MovieCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        mContext = parent.getContext();

        if(mOnClickListener !=null){
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.onClick(view);
                }
            });
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(MovieCardAdapter.ViewHolder holder, int position) {
        holder.mPosition = position;

        Movie movie = mMovieList.get(position);
        String url= "http://image.tmdb.org/t/p/w185/"+movie.getPoster_path();
        holder.mTitle.setText(mMovieList.get(position).getOriginal_title());
        Glide.with(mContext).load(url).into(holder.mImagePopular);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImagePopular;

        int mPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mImagePopular= (ImageView) itemView.findViewById(R.id.popular);
        }

    }


    public MovieCardAdapter(Activity context, String[] mImagesPopular, String[] mTitles) {
        this.mTitles = mTitles;
        this.mImagesPopular = mImagesPopular;
        this.context = context;
    }
}
