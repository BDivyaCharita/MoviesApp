package com.example.divya.moviesapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class FavoriteListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    FavoriteDBHelper favoriteDBHelper;
    Cursor cursor;
    ListFavoriteDataAdapter listFavoriteDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        listView =(ListView) findViewById(R.id.list_view);
        listFavoriteDataAdapter = new ListFavoriteDataAdapter(getApplicationContext(),R.layout.row_favorite_layout);
        listView.setAdapter(listFavoriteDataAdapter);
        favoriteDBHelper = new FavoriteDBHelper(getApplicationContext());
        sqLiteDatabase =favoriteDBHelper.getReadableDatabase();
        cursor = favoriteDBHelper.getInformations(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do{

                String  poster, overview,  date,  title, language, backdrop,  url;
                poster = cursor.getString(0);
                overview = cursor.getString(1);
                date = cursor.getString(2);
                title = cursor.getString(3);
                language =cursor.getString(4);
                backdrop = cursor.getString(5);
                url = cursor.getString(6);

                Movie movie = new Movie(poster,overview,date,title,language,backdrop,url);
                listFavoriteDataAdapter.add(movie);


            }while(cursor.moveToNext());
        }
    }



}
