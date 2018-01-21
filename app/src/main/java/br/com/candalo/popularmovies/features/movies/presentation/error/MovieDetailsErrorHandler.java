package br.com.candalo.popularmovies.features.movies.presentation.error;


import android.content.Context;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.presentation.ErrorHandler;

public class MovieDetailsErrorHandler extends ErrorHandler {

    @Inject
    public MovieDetailsErrorHandler(Context context) {
        super(context);
    }

}
