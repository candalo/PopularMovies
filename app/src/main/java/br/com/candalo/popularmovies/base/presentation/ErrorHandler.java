package br.com.candalo.popularmovies.base.presentation;


import android.content.Context;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.network.NetworkException;

public abstract class ErrorHandler {

    protected Context context;

    public ErrorHandler(Context context) {
        this.context = context;
    }

    public String handleError(Throwable throwable) {
        if (isConnectionError(throwable)) {
            return context.getString(R.string.network_error);
        }
        return context.getString(R.string.default_error);
    }

    private boolean isConnectionError(Throwable throwable) {
        return throwable instanceof NetworkException;
    }
}
