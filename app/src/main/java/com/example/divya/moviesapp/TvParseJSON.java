package com.example.divya.moviesapp;

import android.util.Log;

import com.example.divya.moviesapp.Model.TvShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Divya on 14-02-2017.
 */

public class TvParseJSON {

    public static String[] poster_path;
    public static String[] overview;
    public static String[] first_air_date;
    public static String[] original_name;
    public static String[] original_language;
    public static String[] backdrop_path;
    public static String[] url;
    public  static TvShow tvShow;


    public static final String JSON_ARRAY = "results";
    public static final String KEY_POSTER = "poster_path";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_DATE = "first_air_date";

    public static final String KEY_ORIGINAL_NAME = "original_name";

    public static final String KEY_ORIGINAL_LANGUAGE = "original_language";

    public static final String KEY_BACKDROP = "backdrop_path";

    public static List<TvShow> finalTvShow;




    private JSONArray users = null;
    private String json;

    public TvParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            poster_path = new String[users.length()];
            original_name = new String[users.length()];
            first_air_date = new String[users.length()];
            original_language= new String[users.length()];
            overview= new String[users.length()];
            backdrop_path= new String[users.length()];
            url = new String[users.length()];



            for(int i=0; i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                poster_path[i]= jo.getString(KEY_POSTER);
                original_name[i]= jo.getString(KEY_ORIGINAL_NAME);
                first_air_date[i]= jo.getString(KEY_DATE);
                original_language[i]= jo.getString(KEY_ORIGINAL_LANGUAGE);
                overview[i]= jo.getString(KEY_OVERVIEW);
                backdrop_path[i] = jo.getString(KEY_BACKDROP);
                Log.d("title",original_name[i]);

                tvShow = new TvShow(poster_path[i],original_name[i],first_air_date[i],original_language[i],overview[i],backdrop_path[i],url[i]);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
