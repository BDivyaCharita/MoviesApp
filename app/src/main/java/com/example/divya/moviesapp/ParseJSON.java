package com.example.divya.moviesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Divya on 21-12-2016.
 */

public class ParseJSON{
    public static String[] poster_path;
    public static String[] overview;
    public static String[] release_date;
    public static String[] original_title;
    public static String[] original_language;
    public static String[] backdrop_path;
    public static String[] url;
    public  static Movie movie;


        public static final String JSON_ARRAY = "results";
        public static final String KEY_POSTER = "poster_path";
        public static final String KEY_OVERVIEW = "overview";
         public static final String KEY_RELEASE = "release_date";

        public static final String KEY_ORIGINAL_TITLE = "original_title";

     public static final String KEY_ORIGINAL_LANGUAGE = "original_language";

    public static final String KEY_BACKDROP = "backdrop_path";

    public static List<Movie> finalMovie;




    private JSONArray users = null;
    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            poster_path = new String[users.length()];
            original_title = new String[users.length()];
            release_date = new String[users.length()];
            original_language= new String[users.length()];
            overview= new String[users.length()];
            backdrop_path= new String[users.length()];
            url = new String[users.length()];



            for(int i=0; i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                poster_path[i]= jo.getString(KEY_POSTER);
                original_title[i]= jo.getString(KEY_ORIGINAL_TITLE);
                release_date[i]= jo.getString(KEY_RELEASE);
                original_language[i]= jo.getString(KEY_ORIGINAL_LANGUAGE);
                overview[i]= jo.getString(KEY_OVERVIEW);
                backdrop_path[i] = jo.getString(KEY_BACKDROP);
                Log.d("title",original_title[i]);

                movie = new Movie(poster_path[i],original_title[i],release_date[i],original_language[i],overview[i],backdrop_path[i],url[i]);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
