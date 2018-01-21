package br.com.candalo.popularmovies.features.movies.data.repository;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.data.LocalDataSource;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class MovieRepositoryImpl implements MovieRepository {

    private LocalDataSource<Movie> movieLocalDataSource;

    @Inject
    public MovieRepositoryImpl(LocalDataSource<Movie> movieLocalDataSource) {
        this.movieLocalDataSource = movieLocalDataSource;
    }

    @Override
    public Observable<Void> save(Movie movie) {
        return movieLocalDataSource.save(movie);
    }

    @Override
    public Observable<Movie> find(int movieId) {
        return movieLocalDataSource.find(movieId);
    }

    @Override
    public Observable<List<Movie>> list() {
        return movieLocalDataSource.list();
    }

    @Override
    public Observable<List<Movie>> listByQuery(MovieQuerySpec querySpec) {
        return querySpec.query();
    }

}
