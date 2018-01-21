package br.com.candalo.popularmovies.features.movies.data.datasource.local;


import android.net.Uri;
import android.provider.BaseColumns;

class MovieContract {

    static final String AUTHORITY = "br.com.candalo.popularmovies";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    static final String PATH_MOVIES = "movies";

    static final class MovieEntry implements BaseColumns {

        static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        static final String TABLE_NAME = "movie";
        static final String COLUMN_ID = "id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_POSTER_PATH = "poster_path";
        static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        static final String COLUMN_SYNOPSIS = "synopsis";
        static final String COLUMN_USER_AVERAGE = "user_average";
        static final String COLUMN_RELEASE_DATE = "release_date";

    }

}
