package br.com.candalo.popularmovies.features.movies.util;


import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class MovieUtils {

    private MovieUtils() {
    }

    public static final String MOVIE_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780/";

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context).load(imageUrl).into(imageView);
    }
}
