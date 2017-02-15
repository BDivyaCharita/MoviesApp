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
        sendRequest();
        recyclerView = (RecyclerView) findViewById(R.id.more_recycler_view);

        adapter = new MoreMoviesFinalAdapter(this, allSampleData);
        recyclerView.hasFixedSize();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);


    }

    private void prepareAlbums() {


        Movie a = new Movie("True Romance", "13");
        moreMoviesList.add(a);

        a = new Movie("Xscpae", "8");
        moreMoviesList.add(a);

        a = new Movie("Maroon 5", "11");
        moreMoviesList.add(a);

        a = new Movie("Born to Die", "12");
        moreMoviesList.add(a);

        a = new Movie("Honeymoon", "14");
        moreMoviesList.add(a);

        a = new Movie("I Need a Doctor", "1");
        moreMoviesList.add(a);

        a = new Movie("Loud", "11");
        moreMoviesList.add(a);

        a = new Movie("Legend","14");
        moreMoviesList.add(a);

        a = new Movie("Hello", "11");
        moreMoviesList.add(a);

        a = new Movie("Greatest Hits","17");
        moreMoviesList.add(a);

        adapter.notifyDataSetChanged();
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

        for (int j = 0; j <= 10; j++) {
            singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
        }
        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);


    }

}





