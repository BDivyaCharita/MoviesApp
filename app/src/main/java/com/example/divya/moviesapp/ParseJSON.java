package com.example.divya.moviesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Divya on 21-12-2016.
 */

public class ParseJSON{
    public static String[] titles;
    public static String[] year;

    public static final String JSON_ARRAY = "Search";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_YEAR = "Year";

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

            for(int i=0; i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                titles[i]= (String) jo.get(KEY_TITLE);
                year[i]= (String) jo.get(KEY_YEAR);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
