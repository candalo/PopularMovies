package br.com.candalo.popularmovies.features.movies.domain.repository;


import java.util.List;

import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import io.reactivex.Observable;

public interface MovieRepository {

    Observable<Void> save(Movie movie);

    Observable<Movie> find(int movieId);

    Observable<List<Movie>> list();

    Observable<List<Movie>> listByQuery(MovieQuerySpec querySpec);

}
