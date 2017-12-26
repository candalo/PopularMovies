package br.com.candalo.popularmovies.features.movies.data.repository;


import java.util.List;

import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public Observable<List<Movie>> listByQuery(MovieQuerySpec querySpec) {
        return querySpec.query();
    }

}
