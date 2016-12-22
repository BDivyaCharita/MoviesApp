package com.example.divya.moviesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
    public static Bitmap[] posters;

        public static final String JSON_ARRAY = "Search";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_YEAR = "Year";
        public static final String KEY_POSTER= "Poster";


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
            posters= new Bitmap[users.length()];



            for(int i=0; i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                titles[i]= jo.getString(KEY_TITLE);
                year[i]= jo.getString(KEY_YEAR);

                URL url = new URL(KEY_POSTER);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                posters[i]=bitmap;


            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
