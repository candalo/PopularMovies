package br.com.candalo.popularmovies.features.movies.domain.repository;


import java.util.List;

import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import io.reactivex.Observable;

public interface MovieRepository {

    Observable<List<Movie>> listByQuery(MovieQuerySpec querySpec);

}
