package br.com.candalo.popularmovies.features.movies.presentation.presenter;


import java.util.ArrayList;
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
    }

    @Override
    public void destroy() {
        getMovieTrailersUseCase.dispose();
        view = null;
    }

    public void getMovieTrailers(int movieId) {
        getMovieTrailersUseCase.execute(new GetMovieTrailersObserver(), movieId);
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
}
