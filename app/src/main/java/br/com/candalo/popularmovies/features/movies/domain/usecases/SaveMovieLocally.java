package br.com.candalo.popularmovies.features.movies.domain.usecases;


import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class SaveMovieLocally extends BaseUseCase<Void, Movie> {

    private MovieRepository movieRepository;

    @Inject
    public SaveMovieLocally(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    protected Observable<Void> getObservable(Movie movie) {
        return movieRepository.save(movie);
    }
}
