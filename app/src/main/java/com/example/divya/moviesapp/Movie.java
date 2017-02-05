package com.example.divya.moviesapp;

import java.util.ArrayList;

/**
 * Created by Divya on 02-01-2017.
 */

public class Movie {
    private String poster_path;
    private String overview;
    private String release_date;
    private  String original_title;
    private String original_language;
    private String backdrop_path;
    private String url;
    private ArrayList<Movie> allItems;

    public Movie(String poster_path, String overview, String release_date, String original_title, String original_language, String backdrop_path, String url) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_title = original_title;
        this.original_language = original_language;
        this.backdrop_path = backdrop_path;
        this.url = url;

    }

    public Movie(String original_title, String release_date) {
        this.original_title = original_title;
        this.release_date = release_date;
    }

    public Movie(ArrayList<Movie> allItems) {
        this.allItems= allItems;
    }

    public ArrayList<Movie> getAllItems() {
        return allItems;
    }

    public void setAllItems(ArrayList<Movie> allItems) {
        this.allItems = allItems;
    }

    public Movie(String poster_path, String original_title, String url) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.url = url;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
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
}
