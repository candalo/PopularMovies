package br.com.candalo.popularmovies.features.movies.data.datasource;


import android.content.Context;
import android.net.NetworkInfo;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.BuildConfig;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieResponseData;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.Observable;

public class MoviesByRatingQuerySpec implements MovieQuerySpec {

    private Context context;
    private MovieApi movieApi;

    @Inject
    public MoviesByRatingQuerySpec(Context context, MovieApi movieApi) {
        this.context = context;
        this.movieApi = movieApi;
    }

    @Override
    public Observable<List<Movie>> query() {
        return ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMap(connectivity -> {
                    if(connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        return movieApi
                                .getTopRatedMovies(BuildConfig.ApiKey)
                                .map(MovieResponseData::getMovies);
                    }
                    return Observable.error(new NetworkException());
                });
    }
}
