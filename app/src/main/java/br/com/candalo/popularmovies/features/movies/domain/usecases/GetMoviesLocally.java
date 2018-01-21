package br.com.candalo.popularmovies.features.movies.domain.usecases;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class GetMoviesLocally extends BaseUseCase<List<Movie>, Void> {

    private MovieRepository movieRepository;

    @Inject
    public GetMoviesLocally(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<List<Movie>> getObservable(Void aVoid) {
        return movieRepository.list();
    }
}
