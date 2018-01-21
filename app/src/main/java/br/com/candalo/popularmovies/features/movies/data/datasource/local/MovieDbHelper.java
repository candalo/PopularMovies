package br.com.candalo.popularmovies.features.movies.data.datasource.local;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.com.candalo.popularmovies.features.movies.data.datasource.local.MovieContract.MovieEntry.*;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "movies.db";
    private static final int VERSION = 1;

    MovieDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID                           + " INTEGER PRIMARY KEY, " +
                COLUMN_TITLE                        + " TEXT NOT NULL, " +
                COLUMN_POSTER_PATH                  + " TEXT NOT NULL, " +
                COLUMN_BACKDROP_PATH                + " TEXT NOT NULL, " +
                COLUMN_SYNOPSIS                     + " TEXT NOT NULL, " +
                COLUMN_USER_AVERAGE                 + " REAL NOT NULL, " +
                COLUMN_RELEASE_DATE                 + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
