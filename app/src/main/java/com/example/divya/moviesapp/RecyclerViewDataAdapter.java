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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Divya on 12-01-2017.
 */

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;

    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getHeaderTitle();

        final ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();
        String url = "";

        itemRowHolder.itemTitle.setText(sectionName);
        if(sectionName.equals("Popular")){
            url = "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("Upcoming")){
            url = "https://api.themoviedb.org/3/movie/upcoming?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("Top Rated")){
            url = "https://api.themoviedb.org/3/movie/top_rated?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        else if(sectionName.equals("Now Playing")){
            url = "https://api.themoviedb.org/3/movie/now_playing?api_key=6b7085c6deee4086616c8dae1c1ada12";
        }

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext,singleSectionItems);
        final String URL = url;

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        final String finalUrl = url;
        itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(v.getContext(),MoreMovies.class);
                i.putExtra("url", URL);
                v.getContext().startActivity(i);
                Toast.makeText(v.getContext(), "click event on more, "+ finalUrl, Toast.LENGTH_SHORT).show();
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


