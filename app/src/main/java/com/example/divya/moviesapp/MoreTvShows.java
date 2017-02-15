package com.example.divya.moviesapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.divya.moviesapp.Model.TvSectionDataModel;
import com.example.divya.moviesapp.Model.TvShow;

import java.util.ArrayList;

/**
 * Created by Divya on 15-02-2017.
 */

public class MoreTvShows extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<TvShow> moreMoviesList;
    ArrayList<TvSectionDataModel> allSampleData;
    private MoreTvFinalAdapter adapter;
    Context mContext;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_card);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        allSampleData = new ArrayList<TvSectionDataModel>();
        sendRequest();
        recyclerView = (RecyclerView) findViewById(R.id.more_recycler_view);

        adapter = new MoreTvFinalAdapter(this, allSampleData);
        recyclerView.hasFixedSize();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);


    }

    private void sendRequest(){
        extras = getIntent().getExtras();
        String url = extras.getString("url");

        String JSON_URL= url;

        StringRequest stringRequest = new StringRequest( JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        createData(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void createData(String json) {

        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        String url= "";
        TvSectionDataModel dm = new TvSectionDataModel();
        ArrayList<TvShow> singleItem = new ArrayList<TvShow>();

        for (int j = 0; j <= 10; j++) {
            singleItem.add(new TvShow(TvParseJSON.poster_path[j],TvParseJSON.overview[j],TvParseJSON.first_air_date[j],TvParseJSON.original_name[j],TvParseJSON.original_language[j],TvParseJSON.backdrop_path[j],url));
        }
        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);


    }

}






