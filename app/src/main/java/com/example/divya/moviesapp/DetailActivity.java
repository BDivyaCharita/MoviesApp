package com.example.divya.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

/**
 * Created by Divya on 23-12-2016.
 */

public class DetailActivity extends AppCompatActivity {
    Bundle extras;

    private TextView detailTitle;
    private TextView detailYear;
    private TextView detailIMDBid;
    private TextView detailType;
    private ImageView detailPoster;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        detailTitle = (TextView) findViewById(R.id.textViewTitle);
        detailYear = (TextView) findViewById(R.id.textViewYear);
        detailIMDBid= (TextView)findViewById(R.id.textViewIMDBid);
        detailType= (TextView) findViewById(R.id.textViewType);
        detailPoster = (ImageView) findViewById(R.id.imageViewPoster);
        sendRequest();
    }


    private void sendRequest() {

        extras = getIntent().getExtras();

        if (extras != null) {
            String JSON_URL = extras.getString("url");


            StringRequest stringRequest = new StringRequest(JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showJSON(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }}

    private void showJSON(String json) {

        extras = getIntent().getExtras();
        int position = extras.getInt("position");
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        detailTitle.setText(ParseJSON.original_title[position]);
        detailYear.setText("Year: "+ParseJSON.release_date[position]);
        detailIMDBid.setText("imdbID: "+ParseJSON.original_language[position]);
        detailType.setText("Type: "+ParseJSON.overview[position]);
        Glide.with(this).load(ParseJSON.backdrop_path[position]).placeholder(R.mipmap.ic_launcher).into(detailPoster);

    }

}


