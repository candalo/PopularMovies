package br.com.candalo.popularmovies.features.movies.data.repository;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.data.CloudDataSource;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieReviewRepository;
import io.reactivex.Observable;

public class MovieReviewRepositoryImpl implements MovieReviewRepository {

    private CloudDataSource<List<MovieReview>, Integer> cloudDataSource;

    @Inject
    public MovieReviewRepositoryImpl(CloudDataSource<List<MovieReview>, Integer> cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Observable<List<MovieReview>> listByMovieId(int movieId) {
        return cloudDataSource.call(movieId);
    }
}
