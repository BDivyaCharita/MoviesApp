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

/**
 * Created by Divya on 21-12-2016.
 */

public class ParseJSON{
    public static String[] titles;
    public static String[] year;
    public static String[] imdbID;
    public static String[] type;
    public static String[] poster;


        public static final String JSON_ARRAY = "Search";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_YEAR = "Year";
         public static final String KEY_IMDBID = "imdbID";
        public static final String KEY_TYPE = "Type";
        public static final String KEY_POSTER = "Poster";




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

            titles = new String[users.length()];
            year = new String[users.length()];
            imdbID = new String[users.length()];
            type = new String[users.length()];
            poster = new String[users.length()];


            for(int i=0; i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                titles[i]= jo.getString(KEY_TITLE);
                year[i]= jo.getString(KEY_YEAR);
                type[i]= jo.getString(KEY_TYPE);
                imdbID[i]= jo.getString(KEY_IMDBID);
                poster[i]= jo.getString(KEY_POSTER);
                Log.d("type",type[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
