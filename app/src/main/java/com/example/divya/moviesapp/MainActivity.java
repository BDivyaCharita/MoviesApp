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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        allSampleData = new ArrayList<SectionDataModel>();


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
        String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        createData(response);
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


    public void createData(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        for (int i = 1; i <= 4; i++) {

            SectionDataModel dm = new SectionDataModel();
            ArrayList<Movie> singleItem = new ArrayList<Movie>();
            if(i==1) {
                dm.setHeaderTitle("Popular");

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.original_title[j]));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);

            }
            if(i==2) {
                dm.setHeaderTitle("Top Rated");
                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie("Item " + j, "URL " + j));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);
            }


            if(i==3) {
                dm.setHeaderTitle("Upcoming");
                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie("Item " + j, "URL " + j));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);
            }


            if(i==4) {
                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie("Item " + j, "URL " + j));
                }

                dm.setAllItemsInSection(singleItem);

                allSampleData.add(dm);
            }



        }
    }

}