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

    @Inject
    public MoviePresenter(UseCase<List<Movie>, Void> getMovieListByPopularityUseCase) {
        this.getMovieListByPopularityUseCase = getMovieListByPopularityUseCase;
    }

    @Override
    public void attachTo(MovieView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        getMovieListByPopularityUseCase.dispose();
        view = null;
    }

    public void onCallApiButtonClicked() {
        getMovieListByPopularityUseCase.execute(new GetMovieListByPopularityObserver(), null);
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
            view.showErrorMessage("Ops! An error occurred");
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    }
}
