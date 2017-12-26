package br.com.candalo.popularmovies.features.movies.data.datasource;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.BuildConfig;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieResponseData;
import io.reactivex.Observable;

public class MovieQuerySpecImpl implements MovieQuerySpec {

    private MovieApi movieApi;

    @Inject
    public MovieQuerySpecImpl(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    @Override
    public Observable<List<Movie>> query() {
        return movieApi
                .getTopRatedMovies(BuildConfig.ApiKey)
                .map(MovieResponseData::getMovies);
    }

}
