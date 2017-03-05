package com.example.divya.moviesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by Divya on 02-03-2017.
 */

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Movie> moreMoviesList;
    ArrayList<SectionDataModel> allSampleData;
    private MoreMoviesFinalAdapter adapter;
    private Config config;
  //  private RecyclerView.LayoutManager layoutManager;
   // private RecyclerView.Adapter adapter;
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

       // recyclerView.setHasFixedSize(true);

        //layoutManager = new LinearLayoutManager(this);

        //recyclerView.setLayoutManager(layoutManager);

        //getData();


    }
    /*
    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(SearchActivity.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                parseJSON(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    extras = getIntent().getExtras();
                    String query = extras.getString("query");
                    SearchActivity.this.setTitle(query);
                    String urll = "http://api.themoviedb.org/3/search/movie?api_key=6b7085c6deee4086616c8dae1c1ada12&query=";

                    String JSON_URL= urll+query;

                    URL url = new URL(JSON_URL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    public void showData(){
        String[] url={};
        adapter = new MoreMoviesAdapter(Config.poster_path, Config.overview, Config.release_date, Config.original_title, Config.original_language, Config.backdrop_path, url);
        recyclerView.setAdapter(adapter);
    }

    private void parseJSON(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray(Config.JSON_ARRAY);

            config = new Config(array.length());

            for(int i=0; i<array.length(); i++){
                JSONObject j = array.getJSONObject(i);
                Config.poster_path[i] = getPosterPath(j);
                Config.overview[i] = getOverview(j);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        showData();


    }

    private String getPosterPath(JSONObject j){
        String poster = null;
        try {
            poster = j.getString(Config.KEY_POSTER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return poster;
    }

    private String getOverview(JSONObject j){
        String overview = null;
        try {
            overview = j.getString(Config.KEY_OVERVIEW);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return overview;
    }
*/

    private void sendRequest(){
        extras = getIntent().getExtras();
        String query = extras.getString("query");
        SearchActivity.this.setTitle(query);
        String url = "http://api.themoviedb.org/3/search/movie?api_key=6b7085c6deee4086616c8dae1c1ada12&query=";

        String JSON_URL= url+query;
        Log.d("search url", JSON_URL);

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

        int i = ParseJSON.l;

        SectionDataModel dm = new SectionDataModel();
        ArrayList<Movie> singleItem = new ArrayList<Movie>();

        for ( int j =0; j < i; j++) {

           // if (ParseJSON.poster_path[j] != null) {
                singleItem.add(new Movie(ParseJSON.poster_path[j], ParseJSON.overview[j], ParseJSON.release_date[j], ParseJSON.original_title[j], ParseJSON.original_language[j], ParseJSON.backdrop_path[j], url));
                Log.d("working","search") ;
            //  }

           // else break;
        }

        dm.setAllItemsInSection(singleItem);

        allSampleData.add(dm);
    }



}

