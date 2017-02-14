package com.example.divya.moviesapp;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ArrayList<SectionDataModel> allSampleData;
   private ProgressDialog pd;
    ArrayList<SectionDataModel> detail;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,myToolbar,R.string.drawer_open,R.string.drawer_close);

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        allSampleData = new ArrayList<SectionDataModel>();
        detail = new ArrayList<SectionDataModel>();

        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading...");
        sendRequest();

        my_recycler_view.setHasFixedSize(true);

        pd.show();

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    /* private void initCollapsingToolbar() {

            final CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(" ");
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
            appBarLayout.setExpanded(true);

            // hiding & showing the title when toolbar expanded & collapsed
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbar.setTitle(getString(R.string.app_name));
                        isShow = true;
                    } else if (isShow) {
                        collapsingToolbar.setTitle(" ");
                        isShow = false;
                    }
                }
            });
        }

    */
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);

       MenuItem searchItem = menu.findItem(R.id.action_search);
       SearchView searchView =
               (SearchView) MenuItemCompat.getActionView(searchItem);

       // Configure the search info and add any event listeners...

       return super.onCreateOptionsMenu(menu);
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
                                pd.dismiss();

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

                dm.setHeaderTitle("Popular");
                String url ="https://api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
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
                String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=6b7085c6deee4086616c8dae1c1ada12";

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
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
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=6b7085c6deee4086616c8dae1c1ada12";

                for (int j = 0; j <= 10; j++) {
                    singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
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
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=6b7085c6deee4086616c8dae1c1ada12";

        for (int j = 0; j <= 10; j++) {
            singleItem.add(new Movie(ParseJSON.poster_path[j],ParseJSON.overview[j],ParseJSON.release_date[j],ParseJSON.original_title[j],ParseJSON.original_language[j],ParseJSON.backdrop_path[j],url));
        }

        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);

    }

}