package com.example.divya.moviesapp;

/**
 * Created by Divya on 07-03-2017.
 */

public class FavoriteContract  {

    public static abstract class FavoriteInfo
    {

        public static final String FAV_POSTER ="poster_path";
        public static final String FAV_OVERVIEW ="overview";
        public static final String FAV_DATE= "release_date";
        public static final  String FAV_TITLE= "original_title";
        public static final String FAV_LANGUAGE="original_language";
        public static final String FAV_BACKDROP="backdrop_path";
        public static final String FAV_URL ="url";
        public static final String TABLE_NAME= "fav_info";
    }

}
