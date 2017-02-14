package com.example.divya.moviesapp.Model;

import java.util.ArrayList;

/**
 * Created by Divya on 14-02-2017.
 */

public class TvShow {
    private String poster_path;
    private String overview;
    private String first_air_date;
    private  String original_name;
    private String original_language;
    private String backdrop_path;
    private String url;
    private ArrayList<TvShow> allItems;

    public TvShow(String poster_path, String overview, String first_air_date, String original_name, String original_language, String backdrop_path, String url) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.original_name = original_name;
        this.original_language = original_language;
        this.backdrop_path = backdrop_path;
        this.url = url;
    }

    public TvShow(String original_name, String first_air_date) {
        this.original_name = original_name;
        this.first_air_date = first_air_date;
    }

    public TvShow(ArrayList<TvShow> allItems) {
        this.allItems = allItems;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<TvShow> getAllItems() {
        return allItems;
    }

    public void setAllItems(ArrayList<TvShow> allItems) {
        this.allItems = allItems;
    }
}
