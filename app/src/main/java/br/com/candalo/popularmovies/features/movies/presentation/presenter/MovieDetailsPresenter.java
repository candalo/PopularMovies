package br.com.candalo.popularmovies.features.movies.presentation.presenter;


import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.base.presentation.ErrorHandler;
import br.com.candalo.popularmovies.base.presentation.Presenter;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieDetailsView;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.observers.DisposableObserver;

public class MovieDetailsPresenter implements Presenter<MovieDetailsView> {

    private MovieDetailsView view;
    private UseCase<List<Video>, Integer> getMovieTrailersUseCase;
    private ErrorHandler errorHandler;

    @Inject
    public MovieDetailsPresenter(UseCase<List<Video>, Integer> getMovieTrailersUseCase,
                                 ErrorHandler errorHandler) {
        this.getMovieTrailersUseCase = getMovieTrailersUseCase;
        this.errorHandler = errorHandler;
    }

    @Override
    public void attachTo(MovieDetailsView view) {
        this.view = view;
        //TODO: Remove static data
        getMovieTrailersUseCase.execute(new GetMovieTrailersObserver(), 346364);
    }

    @Override
    public void destroy() {
        getMovieTrailersUseCase.dispose();
        view = null;
    }

    class GetMovieTrailersObserver extends DisposableObserver<List<Video>> {
        @Override
        public void onNext(List<Video> videos) {

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
            view.hideLoading();
        }
    }
}
