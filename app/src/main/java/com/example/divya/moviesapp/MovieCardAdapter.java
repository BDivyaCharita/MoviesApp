package com.example.divya.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {


    private List<String> mMovieList;
    private Context mContext;
    private View.OnClickListener mOnClickListener;

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
        holder.mTitle.setText(mMovieList.get(position));




    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
