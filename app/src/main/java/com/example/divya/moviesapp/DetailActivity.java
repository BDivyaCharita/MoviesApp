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
    private TextView detailOverview;
    private TextView detailType;
    private ImageView detailPoster;
    private ImageView detailBackdrop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        detailTitle = (TextView) findViewById(R.id.textViewTitle);
        detailYear = (TextView) findViewById(R.id.textViewYear);
        detailOverview= (TextView)findViewById(R.id.textViewIMDBid);
        detailType= (TextView) findViewById(R.id.textViewType);
        detailPoster = (ImageView) findViewById(R.id.imageViewPoster);
        detailBackdrop = (ImageView)findViewById(R.id.backdrop);

        extras = getIntent().getExtras();

        if (extras != null) {
            String original_title = extras.getString("title");
            String release_date = extras.getString("date");
            String original_language = extras.getString("language");
            String overview = extras.getString("overview");
            String poster_path = extras.getString("poster");
            String backdrop_path = extras.getString("backdrop");

            String urlBackdrop ="http://image.tmdb.org/t/p/w185/"+original_language;
            String urlPoster = "http://image.tmdb.org/t/p/w185/"+poster_path;

            detailTitle.setText(overview);
            detailYear.setText("Date:" + release_date);
            detailType.setText("Language: " + backdrop_path);
            detailOverview.setText("Overview: " + original_title);
            Glide.with(this).load(urlPoster).placeholder(R.mipmap.ic_launcher).into(detailPoster);
            Glide.with(this).load(urlBackdrop).placeholder(R.mipmap.ic_launcher).into(detailBackdrop);

        }
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
        String u = "http://image.tmdb.org/t/p/w185/";

        extras = getIntent().getExtras();
        int position = extras.getInt("position");
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        detailTitle.setText(ParseJSON.original_title[position]);
        detailYear.setText("Date: "+ParseJSON.release_date[position]);
        detailOverview.setText("imdbID: "+ParseJSON.original_language[position]);
        detailType.setText("Type: "+ParseJSON.overview[position]);
        Glide.with(this).load(u+ParseJSON.poster_path[position]).placeholder(R.mipmap.ic_launcher).into(detailPoster);
        Glide.with(this).load(u+ParseJSON.backdrop_path[position]).placeholder(R.mipmap.ic_launcher).into(detailBackdrop);


    }

}


