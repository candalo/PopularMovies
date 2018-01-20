package br.com.candalo.popularmovies.features.movies.data.repository;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.data.CloudDataSource;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieTrailerRepository;
import io.reactivex.Observable;

public class MovieTrailerRepositoryImpl implements MovieTrailerRepository {

    private CloudDataSource<List<Video>, Integer> cloudDataSource;

    @Inject
    public MovieTrailerRepositoryImpl(CloudDataSource<List<Video>, Integer> cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Observable<List<Video>> listByMovieId(int movieId) {
        return cloudDataSource.call(movieId);
    }
}
