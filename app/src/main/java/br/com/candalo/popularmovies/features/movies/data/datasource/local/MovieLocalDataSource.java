package br.com.candalo.popularmovies.features.movies.data.datasource.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.data.LocalDataSource;
import br.com.candalo.popularmovies.features.movies.domain.errors.MovieNotFoundException;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import io.reactivex.Observable;

public class MovieLocalDataSource implements LocalDataSource<Movie> {

    private Context context;

    @Inject
    public MovieLocalDataSource(Context context) {
        this.context = context;
    }


    @Override
    public Observable<Void> save(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry.COLUMN_ID, movie.getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdropThumbnail());
        contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, movie.getPosterThumbnail());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(MovieContract.MovieEntry.COLUMN_SYNOPSIS, movie.getSynopsis());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_USER_AVERAGE, movie.getUserAverage());

        try {
            Uri uri = context.getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

            if (uri != null) {
                return Observable.empty();
            }

            return Observable.error(new Exception());
        } catch (Exception e) {
            return Observable.error(new Exception());
        }
    }

    @Override
    public Observable<Movie> find(int movieId) {
        String where = MovieContract.MovieEntry.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(movieId)};

        Cursor cursor = context.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                null,
                where,
                whereArgs,
                null);

        if (cursor == null) {
            return Observable.error(new Exception());
        }

        if (!cursor.moveToNext()) {
           return Observable.error(new MovieNotFoundException());
        }

        int id = cursor.getInt(cursor
                .getColumnIndex(MovieContract.MovieEntry.COLUMN_ID));
        String backdropThumbnail = cursor
                .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH));
        String posterThumbnail = cursor
                .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH));
        String releaseDate = cursor
                .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE));
        String synopsis = cursor
                .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS));
        String title = cursor
                .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE));
        double userAverage = cursor
                .getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_USER_AVERAGE));

        Movie movie = new Movie(id, title, posterThumbnail, backdropThumbnail, synopsis, userAverage, releaseDate);

        cursor.close();

        return Observable.just(movie);
    }

    @Override
    public Observable<List<Movie>> list() {
        Cursor cursor = context.getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI, null, null, null, null);

        if (cursor == null) {
            return Observable.error(new Exception());
        }

        List<Movie> movies = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MovieContract.MovieEntry.COLUMN_ID));
            String backdropThumbnail = cursor
                    .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH));
            String posterThumbnail = cursor
                    .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH));
            String releaseDate = cursor
                    .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE));
            String synopsis = cursor
                    .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS));
            String title = cursor
                    .getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE));
            double userAverage = cursor
                    .getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_USER_AVERAGE));

            Movie movie = new Movie(id, title, posterThumbnail, backdropThumbnail, synopsis, userAverage, releaseDate);
            movies.add(movie);
        }

        cursor.close();

        return Observable.just(movies);
    }
}
