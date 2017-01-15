package com.example.divya.moviesapp;

import java.util.ArrayList;

/**
 * Created by Divya on 12-01-2017.
 */

public class MainMovieModel {

    private String headerTitle;
    private ArrayList<Movie> itemsInMovie;

    public MainMovieModel(){

    }

    public MainMovieModel(String headerTitle, ArrayList<Movie> itemsInMovie) {
        this.headerTitle = headerTitle;
        this.itemsInMovie = itemsInMovie;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Movie> getItemsInMovie() {
        return itemsInMovie;
    }

    public void setItemsInMovie(ArrayList<Movie> itemsInMovie) {
        this.itemsInMovie = itemsInMovie;
    }
}
