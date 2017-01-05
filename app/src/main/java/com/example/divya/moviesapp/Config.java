package com.example.divya.moviesapp;

import android.graphics.Bitmap;

/**
 * Created by Divya on 02-01-2017.
 */

public class Config {
    public static String[] names;
    public static String[] urls;
    public  static  Bitmap[] posters;


    public static final String GET_URL="http://www.api.themoviedb.org/3/movie/popular?api_key=6b7085c6deee4086616c8dae1c1ada12";
    public static final String KEY_TITLE = "original_title";
    public static final String KEY_YEAR = "release_date";
    public static final String KEY_POSTER ="poster_path";
    public static final String TAG_JSON_ARRAY="result";

    public Config(int i){
        names = new String[i];
        urls = new String[i];
        posters= new Bitmap[i];

    }
}
