package com.example.divya.moviesapp.Model;

import java.util.ArrayList;

/**
 * Created by Divya on 14-02-2017.
 */

public class TvSectionDataModel {

    private String headerTitle;
    private ArrayList<TvShow> allItemsInSection;

    public TvSectionDataModel(){

    }

    public TvSectionDataModel(String headerTitle, ArrayList<TvShow> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public TvSectionDataModel(ArrayList<TvShow> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<TvShow> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<TvShow> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
