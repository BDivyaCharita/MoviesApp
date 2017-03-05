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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divya on 02-02-2017.
 */

public class MoreMovies extends AppCompatActivity {

   private RecyclerView recyclerView;
    private ArrayList<Movie> moreMoviesList;
    ArrayList<SectionDataModel> allSampleData;
    private MoreMoviesFinalAdapter adapter;
    Context mContext;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_card);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        allSampleData = new ArrayList<SectionDataModel>();

        recyclerView = (RecyclerView) findViewById(R.id.more_recycler_view);

        adapter = new MoreMoviesFinalAdapter(this, allSampleData);
        recyclerView.hasFixedSize();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        sendRequest();
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
        SectionDataModel dm = new SectionDataModel();
        ArrayList<Movie> singleItem = new ArrayList<Movie>();
        int i = ParseJSON.l;

        for (int j = 0; j < i; j++) {
            singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
        }
        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);
    }

}
