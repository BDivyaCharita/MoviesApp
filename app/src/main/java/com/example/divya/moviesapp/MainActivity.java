package com.example.divya.moviesapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<SectionDataModel> allSampleData;
   private ProgressDialog pd;
    ArrayList<SectionDataModel> detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        allSampleData = new ArrayList<SectionDataModel>();
        detail = new ArrayList<SectionDataModel>();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        sendRequest();

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);


    }

    private void sendRequest(){
        String JSON_URL="";
        final String JSON_URL_POPULAR = "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
        final String JSON_URL_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=6b7085c6deee4086616c8dae1c1ada12";
        final String JSON_URL_UPCOMING = "https://api.themoviedb.org/3/movie/upcoming?api_key=6b7085c6deee4086616c8dae1c1ada12";
        final String JSON_URL_NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=6b7085c6deee4086616c8dae1c1ada12";



        String URL = "https://api.themoviedb.org/3/movie/";
        String API_KEY = "?api_key=6b7085c6deee4086616c8dae1c1ada12";
        String POPULAR = "popular";
        String TOP_RATED= "top_rated";
        String UPCOMING = "upcoming";
        String NOW_PLAYING = "now_playing";

        for (int i = 1; i <=4 ; i++) {
            if(i==1){
                JSON_URL = JSON_URL_POPULAR;
                StringRequest stringRequest = new StringRequest( JSON_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                createDataPopular(response);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
                else if(i==2){
                JSON_URL = JSON_URL_TOP_RATED;
                StringRequest stringRequest = new StringRequest( JSON_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                createDataTop(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
            else if(i==3){
                JSON_URL = JSON_URL_UPCOMING;
                StringRequest stringRequest = new StringRequest( JSON_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                createDataUpcoming(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
            else{
                JSON_URL = JSON_URL_NOW_PLAYING;
                StringRequest stringRequest = new StringRequest( JSON_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                createDataNowPlaying(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }




        }


    }


    public void createDataPopular(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

            SectionDataModel dm = new SectionDataModel();
            ArrayList<Movie> singleItem = new ArrayList<Movie>();
            ArrayList<Movie> singleItemDetail = new ArrayList<Movie>();

                dm.setHeaderTitle("Popular");

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j]));
                    singleItemDetail.add(new Movie(ParseJSON.poster_path[j],ParseJSON.original_title[j],ParseJSON.release_date[j],ParseJSON.overview[j],ParseJSON.backdrop_path[j],ParseJSON.original_language[j]));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);


          }


    public void createDataTop(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

            SectionDataModel dm = new SectionDataModel();
            ArrayList<Movie> singleItem = new ArrayList<Movie>();
                dm.setHeaderTitle("Top Rated");

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j]));
                }

                dm.setAllItemsInSection(singleItem);
                allSampleData.add(dm);


    }

    public void createDataUpcoming(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

            SectionDataModel dm = new SectionDataModel();
            ArrayList<Movie> singleItem = new ArrayList<Movie>();

                dm.setHeaderTitle("Upcoming");

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j]));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);


    }

    public void createDataNowPlaying(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        SectionDataModel dm = new SectionDataModel();
        ArrayList<Movie> singleItem = new ArrayList<Movie>();

        dm.setHeaderTitle("Now Playing");

        for (int j = 0; j <= 10; j++) {
            singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j]));
        }

        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);


    }

}