package com.example.divya.moviesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Divya on 07-03-2017.
 */

public class FavoriteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FAVORITEINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ FavoriteContract.FavoriteInfo.TABLE_NAME+"(" + FavoriteContract.FavoriteInfo.FAV_POSTER+" TEXT,"
                    + FavoriteContract.FavoriteInfo.FAV_OVERVIEW+" TEXT,"+ FavoriteContract.FavoriteInfo.FAV_DATE+" TEXT,"
                    + FavoriteContract.FavoriteInfo.FAV_TITLE+" TEXT,"+ FavoriteContract.FavoriteInfo.FAV_LANGUAGE+" TEXT,"
                    + FavoriteContract.FavoriteInfo.FAV_BACKDROP+" TEXT,"+ FavoriteContract.FavoriteInfo.FAV_URL+" TEXT);";

    public FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DataBaseOperations", "DB created/opened");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e("DataBaseOperations", "Table Created");

    }

    public void addInformation(String poster, String overview, String date, String title, String language, String backdrop, String url, SQLiteDatabase db ){

        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_POSTER, poster);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_OVERVIEW,overview);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_DATE,date);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_TITLE,title);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_LANGUAGE,language);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_BACKDROP,backdrop);
        contentValues.put(FavoriteContract.FavoriteInfo.FAV_URL,url);
        db.insert(FavoriteContract.FavoriteInfo.TABLE_NAME,null,contentValues);
        Log.e("DataBaseOperations", "1 row inserted");
        Log.e("Overview", overview);

    }

    public Cursor getInformations(SQLiteDatabase db)
    {

        Cursor cursor;
        String[] projections = {FavoriteContract.FavoriteInfo.FAV_POSTER, FavoriteContract.FavoriteInfo.FAV_OVERVIEW,
                FavoriteContract.FavoriteInfo.FAV_DATE, FavoriteContract.FavoriteInfo.FAV_TITLE,
                FavoriteContract.FavoriteInfo.FAV_LANGUAGE, FavoriteContract.FavoriteInfo.FAV_BACKDROP,
                FavoriteContract.FavoriteInfo.FAV_URL};

        cursor=db.query(FavoriteContract.FavoriteInfo.TABLE_NAME, projections,null,null,null,null,null);

        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
