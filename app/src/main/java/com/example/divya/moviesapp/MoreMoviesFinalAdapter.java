package com.example.divya.moviesapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_final, null);
        ItemHolder mh = new ItemHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final ArrayList singleSectionItems = dataList.get(position).getAllItemsInSection();

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext,singleSectionItems);

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
