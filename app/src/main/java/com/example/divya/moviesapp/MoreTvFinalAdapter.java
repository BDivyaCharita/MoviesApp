package com.example.divya.moviesapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divya.moviesapp.Model.TvSectionDataModel;

import java.util.ArrayList;

/**
 * Created by Divya on 15-02-2017.
 */

public class MoreTvFinalAdapter extends RecyclerView.Adapter<MoreTvFinalAdapter.ItemHolder> {

    private ArrayList<TvSectionDataModel> dataList;
    private Context mContext;

    public MoreTvFinalAdapter( Context mContext, ArrayList<TvSectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public MoreTvFinalAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_final, null);
        MoreTvFinalAdapter.ItemHolder mh = new MoreTvFinalAdapter.ItemHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MoreTvFinalAdapter.ItemHolder holder, int position) {
        final ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();

        MoreTvAdapter itemListDataAdapter = new MoreTvAdapter(mContext,singleSectionItems);

        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        protected RecyclerView recycler_view_list;
        public ItemHolder(View itemView) {
            super(itemView);
            this.recycler_view_list = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);

        }
    }
}

