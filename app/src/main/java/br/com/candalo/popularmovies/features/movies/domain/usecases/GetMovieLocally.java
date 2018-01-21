package br.com.candalo.popularmovies.features.movies.domain.usecases;


import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class GetMovieLocally extends BaseUseCase<Movie, Integer> {

    private MovieRepository movieRepository;

    @Inject
    public GetMovieLocally(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<Movie> getObservable(Integer movieId) {
        return movieRepository.find(movieId);
    }
}
