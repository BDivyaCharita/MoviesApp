package com.example.divya.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Divya on 05-02-2017.
 */

public class MoreMoviesFinalAdapter extends RecyclerView.Adapter<MoreMoviesFinalAdapter.ItemHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;

    public MoreMoviesFinalAdapter( Context mContext, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }
}
