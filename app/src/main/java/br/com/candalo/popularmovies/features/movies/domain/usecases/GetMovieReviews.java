package br.com.candalo.popularmovies.features.movies.domain.usecases;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.BaseUseCase;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieReviewRepository;
import io.reactivex.Observable;

public class GetMovieReviews extends BaseUseCase<List<MovieReview>, Integer> {

    private MovieReviewRepository movieReviewRepository;

    @Inject
    public GetMovieReviews(MovieReviewRepository movieReviewRepository) {
        this.movieReviewRepository = movieReviewRepository;
    }

    @Override
    protected Observable<List<MovieReview>> getObservable(Integer movieId) {
        return movieReviewRepository.listByMovieId(movieId);
    }
}
