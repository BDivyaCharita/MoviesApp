package com.example.divya.moviesapp;

import java.util.ArrayList;

/**
 * Created by Divya on 12-01-2017.
 */

public class SectionDataModel {

    private String headerTitle;
    private ArrayList<Movie> allItemsInSection;


    public SectionDataModel() {

    }
    public SectionDataModel(String headerTitle, ArrayList<Movie> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }

    public SectionDataModel(ArrayList<Movie> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Movie> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<Movie> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
