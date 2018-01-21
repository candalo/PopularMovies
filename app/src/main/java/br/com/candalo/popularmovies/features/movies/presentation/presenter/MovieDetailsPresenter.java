package br.com.candalo.popularmovies.features.movies.presentation.presenter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.base.presentation.ErrorHandler;
import br.com.candalo.popularmovies.base.presentation.Presenter;
import br.com.candalo.popularmovies.features.movies.domain.errors.MovieNotFoundException;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieDetailsView;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.observers.DisposableObserver;

public class MovieDetailsPresenter implements Presenter<MovieDetailsView> {

    private MovieDetailsView view;
    private UseCase<List<Video>, Integer> getMovieTrailersUseCase;
    private UseCase<List<MovieReview>, Integer> getMovieReviewsUseCase;
    private UseCase<Void, Movie> saveMovieUseCase;
    private UseCase<Movie, Integer> getMovieUseCase;
    private ErrorHandler errorHandler;

    @Inject
    public MovieDetailsPresenter(UseCase<List<Video>, Integer> getMovieTrailersUseCase,
                                 UseCase<List<MovieReview>, Integer> getMovieReviewsUseCase,
                                 UseCase<Void, Movie> saveMovieUseCase,
                                 UseCase<Movie, Integer> getMovieUseCase,
                                 ErrorHandler errorHandler) {
        this.getMovieTrailersUseCase = getMovieTrailersUseCase;
        this.getMovieReviewsUseCase = getMovieReviewsUseCase;
        this.saveMovieUseCase = saveMovieUseCase;
        this.getMovieUseCase = getMovieUseCase;
        this.errorHandler = errorHandler;
    }

    @Override
    public void attachTo(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        getMovieUseCase.dispose();
        getMovieTrailersUseCase.dispose();
        getMovieReviewsUseCase.dispose();
        saveMovieUseCase.dispose();
        view = null;
    }

    public void loadMovieDetails(int movieId) {
        getMovieUseCase.execute(new GetMovieObserver(), movieId);
        getMovieTrailersUseCase.execute(new GetMovieTrailersObserver(), movieId);
        getMovieReviewsUseCase.execute(new GetMovieReviewsObserver(), movieId);
    }

    public void onMovieStarred(Movie movie) {
        saveMovieUseCase.execute(new SaveMovieObserver(), movie);
    }

    class GetMovieTrailersObserver extends DisposableObserver<List<Video>> {
        @Override
        public void onNext(List<Video> videos) {
            view.onTrailersLoaded(filterVideos(videos));
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

        private List<Video> filterVideos(List<Video> videos) {
            List<Video> filteredVideos = new ArrayList<>();

            for (Video video : videos) {
                boolean isHostedInYoutube = video.getSite().equals(Video.Properties.SITE.getProperty());
                boolean isATrailer = video.getType().equals(Video.Properties.TYPE.getProperty());

                if (isHostedInYoutube && isATrailer) {
                    filteredVideos.add(video);
                }
            }

            return filteredVideos;
        }
    }

    class GetMovieReviewsObserver extends DisposableObserver<List<MovieReview>> {
        @Override
        public void onNext(List<MovieReview> movieReviews) {
            view.onReviewsLoaded(movieReviews);
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

    class SaveMovieObserver extends DisposableObserver<Void> {
        @Override
        public void onNext(Void aVoid) {

        }

        @Override
        public void onError(Throwable e) {
            // TODO: Handle this error
        }

        @Override
        public void onComplete() {

        }
    }

    class GetMovieObserver extends DisposableObserver<Movie> {
        @Override
        public void onNext(Movie movie) {
            view.showStarred();
        }

        @Override
        public void onError(Throwable e) {
            if (e instanceof MovieNotFoundException) {
                view.showUnstarred();
            }
        }

        @Override
        public void onComplete() {

        }
    }
}
