package br.com.candalo.popularmovies.features.movies.domain.usecases;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieTrailerRepository;
import io.reactivex.Observable;

public class GetMovieTrailers extends BaseUseCase<List<Video>, Integer> {

    private MovieTrailerRepository movieTrailerRepository;

    @Inject
    public GetMovieTrailers(MovieTrailerRepository movieTrailerRepository) {
        this.movieTrailerRepository = movieTrailerRepository;
    }

    @Override
    protected Observable<List<Video>> getObservable(Integer movieId) {
        return movieTrailerRepository.listByMovieId(movieId);
    }
}
