package com.example.divya.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divya.moviesapp.Model.TvSectionDataModel;

import java.util.ArrayList;

/**
 * Created by Divya on 14-02-2017.
 */

public class TvRecyclerViewDataAdapter extends RecyclerView.Adapter<TvRecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<TvSectionDataModel> dataList;
    private Context mContext;

    public TvRecyclerViewDataAdapter(Context context, ArrayList<TvSectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public TvRecyclerViewDataAdapter.ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        TvRecyclerViewDataAdapter.ItemRowHolder mh = new TvRecyclerViewDataAdapter.ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(TvRecyclerViewDataAdapter.ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();

        final ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();
        String url = "";

        itemRowHolder.itemTitle.setText(sectionName);
        if(sectionName.equals("Popular")){
            url = "https://api.themoviedb.org/3/tv/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("Top Rated")){
            url = "https://api.themoviedb.org/3/tv/top_rated?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("On TV")){
            url = "https://api.themoviedb.org/3/tv/on_the_air?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("Airing Today")){
            url = "https://api.themoviedb.org/3/movie/airing_today?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        TvSectionListDataAdapter itemListDataAdapter = new TvSectionListDataAdapter(mContext,singleSectionItems);
        final String URL = url;

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(v.getContext(),MoreMovies.class);
                i.putExtra("url", URL);
                v.getContext().startActivity(i);
                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();
            }
        });


      /* Glide.with(mContext)
                .load(itemRowHolder.get)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(feedListRowHolder.thumbView);
                */
    }
    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;



        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.btnMore= (Button) view.findViewById(R.id.btnMore);


        }

    }

}

