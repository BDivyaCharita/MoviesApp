package com.example.divya.moviesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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
    private FloatingActionButton myFab;
    SQLiteDatabase sqLiteDatabase;
    FavoriteDBHelper favoriteDBHelper;
    Context context= this;

    private String original_title, release_date, original_language, overview, poster_path, backdrop_path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setStatusBarTranslucent(true);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        DetailActivity.this.setTitle("");

        detailTitle = (TextView) findViewById(R.id.textViewTitle);
        detailYear = (TextView) findViewById(R.id.textViewYear);
        detailOverview= (TextView)findViewById(R.id.textViewIMDBid);
        detailType= (TextView) findViewById(R.id.textViewType);
        detailPoster = (ImageView) findViewById(R.id.imageViewPoster);
        detailBackdrop = (ImageView)findViewById(R.id.backdrop);

        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(myFab.getId()==R.id.fab) {
                    myFab.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.ic_favorite_white_24dp));
                    addFavorite(v);

                }
            }
        });

        extras = getIntent().getExtras();

        if (extras != null) {
             original_title = extras.getString("title");
             release_date = extras.getString("date");
             original_language = extras.getString("language");
             overview = extras.getString("overview");
             poster_path = extras.getString("poster");
             backdrop_path = extras.getString("backdrop");


            String urlBackdrop ="http://image.tmdb.org/t/p/w780/"+backdrop_path;
            String urlPoster = "http://image.tmdb.org/t/p/w185/"+poster_path;

            detailTitle.setText(original_title);
            detailYear.setText(release_date);
            detailType.setText(original_language);
            detailOverview.setText(overview);
            Glide.with(this).load(urlBackdrop).placeholder(R.mipmap.ic_launcher).into(detailBackdrop);
            Glide.with(this).load(urlPoster).placeholder(R.mipmap.ic_launcher).into(detailPoster);
        }
    }

    public void addFavorite(View view){
        String poster = poster_path;
        String overview = this.overview;
        String date = release_date;
        String title = original_title;
        String language = original_language;
        String backdrop = backdrop_path;
        String url = backdrop_path;

        favoriteDBHelper = new FavoriteDBHelper(context);
        sqLiteDatabase = favoriteDBHelper.getWritableDatabase();
        favoriteDBHelper.addInformation(poster,overview,date,title,language,backdrop,url,sqLiteDatabase);

       // Toast.makeText(context, "DATA SAVED", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, overview, Toast.LENGTH_SHORT).show();
        favoriteDBHelper.close();
    }
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

   /* private void sendRequest() {

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
        String v ="http://image.tmdb.org/t/p/w780/";

        extras = getIntent().getExtras();
        int position = extras.getInt("position");
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        detailTitle.setText(ParseJSON.original_title[position]);
        detailYear.setText(ParseJSON.release_date[position]);
        detailOverview.setText(ParseJSON.original_language[position]);
        detailType.setText(ParseJSON.overview[position]);
        Glide.with(this).load(u+ParseJSON.poster_path[position]).placeholder(R.mipmap.ic_launcher).into(detailPoster);
        Glide.with(this).load(v+ParseJSON.backdrop_path[position]).placeholder(R.mipmap.ic_launcher).into(detailBackdrop);


    }
    */

}


