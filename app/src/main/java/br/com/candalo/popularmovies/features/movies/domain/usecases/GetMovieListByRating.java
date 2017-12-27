package br.com.candalo.popularmovies.features.movies.domain.usecases;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import io.reactivex.Observable;

public class GetMovieListByRating extends BaseUseCase<List<Movie>, Void> {

    private MovieRepository movieRepository;
    private MovieQuerySpec movieQuerySpec;

    @Inject
    public GetMovieListByRating(MovieRepository movieRepository, MovieQuerySpec movieQuerySpec) {
        this.movieRepository = movieRepository;
        this.movieQuerySpec = movieQuerySpec;
    }

    @Override
    protected Observable<List<Movie>> getObservable(Void aVoid) {
        return movieRepository.listByQuery(movieQuerySpec);
    }

}
