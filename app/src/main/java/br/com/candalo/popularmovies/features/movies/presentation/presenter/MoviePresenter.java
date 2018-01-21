package br.com.candalo.popularmovies.features.movies.presentation.presenter;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.base.presentation.ErrorHandler;
import br.com.candalo.popularmovies.base.presentation.Presenter;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieView;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.observers.DisposableObserver;

public class MoviePresenter implements Presenter<MovieView> {

    private MovieView view;
    private UseCase<List<Movie>, Void> getMovieListByPopularityUseCase;
    private UseCase<List<Movie>, Void> getMovieListByRatingUseCase;
    private UseCase<List<Movie>, Void> getStarredMoviesUseCase;
    private ErrorHandler errorHandler;

    @Inject
    public MoviePresenter(UseCase<List<Movie>, Void> getMovieListByPopularityUseCase,
                          UseCase<List<Movie>, Void> getMovieListByRatingUseCase,
                          UseCase<List<Movie>, Void> getStarredMoviesUseCase,
                          ErrorHandler errorHandler) {
        this.getMovieListByPopularityUseCase = getMovieListByPopularityUseCase;
        this.getMovieListByRatingUseCase = getMovieListByRatingUseCase;
        this.getStarredMoviesUseCase = getStarredMoviesUseCase;
        this.errorHandler = errorHandler;
    }

    @Override
    public void attachTo(MovieView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        getMovieListByPopularityUseCase.dispose();
        getMovieListByRatingUseCase.dispose();
        getStarredMoviesUseCase.dispose();
        view = null;
    }

    public void onGetMoviesByPopularityOptionSelected() {
        getMoviesByPopularity();
    }

    public void onGetMoviesByRatingOptionSelected() {
        getMoviesByRating();
    }

    public void onGetStarredMoviesOptionSelected() {
        getStarredMovies();
    }

    public void showLoadedMovies(List<Movie> movies) {
        view.onMoviesLoaded(movies);
    }

    private void getMoviesByPopularity() {
        getMovieListByPopularityUseCase.execute(new GetMovieListByPopularityObserver(), null);
        view.showLoading();
    }

    private void getMoviesByRating() {
        getMovieListByRatingUseCase.execute(new GetMovieListByRatingObserver(), null);
        view.showLoading();
    }

    private void getStarredMovies() {
        getStarredMoviesUseCase.execute(new GetStarredMoviesObserver(), null);
        view.showLoading();
    }

    class GetMovieListByPopularityObserver extends DisposableObserver<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            view.hideLoading();
            view.onMoviesLoaded(movies);
        }

        @Override
        public void onError(Throwable e) {
            view.hideLoading();
            view.showErrorMessage(errorHandler.handleError(e));
            if (e instanceof NetworkException) {
                view.showNetworkErrorMessage();
            }
        }

        @Override
        public void onComplete() {
        }
    }

    class GetMovieListByRatingObserver extends DisposableObserver<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            view.hideLoading();
            view.onMoviesLoaded(movies);
        }

        @Override
        public void onError(Throwable e) {
            view.hideLoading();
            view.showErrorMessage(errorHandler.handleError(e));
            if (e instanceof NetworkException) {
                view.showNetworkErrorMessage();
            }
        }

        @Override
        public void onComplete() {
        }
    }

    class GetStarredMoviesObserver extends DisposableObserver<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            view.hideLoading();
            view.onMoviesLoaded(movies);
        }

        @Override
        public void onError(Throwable e) {
            view.hideLoading();
            view.showErrorMessage(errorHandler.handleError(e));
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    }
}
