package br.com.candalo.popularmovies.features.movies.presentation.presenter;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.base.presentation.Presenter;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieView;
import io.reactivex.observers.DisposableObserver;

public class MoviePresenter implements Presenter<MovieView> {

    private MovieView view;
    private UseCase<List<Movie>, Void> getMovieListByPopularityUseCase;
    private UseCase<List<Movie>, Void> getMovieListByRatingUseCase;

    @Inject
    public MoviePresenter(UseCase<List<Movie>, Void> getMovieListByPopularityUseCase,
                          UseCase<List<Movie>, Void> getMovieListByRatingUseCase) {
        this.getMovieListByPopularityUseCase = getMovieListByPopularityUseCase;
        this.getMovieListByRatingUseCase = getMovieListByRatingUseCase;
    }

    @Override
    public void attachTo(MovieView view) {
        this.view = view;
        getMoviesByPopularity();
    }

    @Override
    public void destroy() {
        getMovieListByPopularityUseCase.dispose();
        view = null;
    }

    public void onGetMoviesByPopularityOptionSelected() {
        getMoviesByPopularity();
    }

    public void onGetMoviesByRatingOptionSelected() {
        getMoviesByRating();
    }

    private void getMoviesByPopularity() {
        getMovieListByPopularityUseCase.execute(new GetMovieListByPopularityObserver(), null);
        view.showLoading();
    }

    private void getMoviesByRating() {
        getMovieListByRatingUseCase.execute(new GetMovieListByRatingObserver(), null);
        view.showLoading();
    }

    class GetMovieListByPopularityObserver extends DisposableObserver<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            view.onMoviesLoaded(movies);
        }

        @Override
        public void onError(Throwable e) {
            view.hideLoading();
            //TODO: Show error message
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    }

    class GetMovieListByRatingObserver extends DisposableObserver<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            view.onMoviesLoaded(movies);
        }

        @Override
        public void onError(Throwable e) {
            view.hideLoading();
            //TODO: Show error message
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    }
}
