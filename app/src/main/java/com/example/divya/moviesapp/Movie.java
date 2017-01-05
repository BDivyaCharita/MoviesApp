package com.example.divya.moviesapp;

import android.graphics.Bitmap;

/**
 * Created by Divya on 02-01-2017.
 */

public class Movie {
   // private int numOfSongs;\
    private int thumbnail;

    public Movie(){

    }

    public Movie( int thumbnail){

     //   this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }


    //public int getNumOfSongs() {
      //  return numOfSongs;
    //}

    //public void setNumOfSongs(int numOfSongs) {
      //  this.numOfSongs = numOfSongs;
    //}

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
