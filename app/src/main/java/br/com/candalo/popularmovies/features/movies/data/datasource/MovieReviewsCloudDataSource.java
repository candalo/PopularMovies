package br.com.candalo.popularmovies.features.movies.data.datasource;


import android.content.Context;
import android.net.NetworkInfo;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.BuildConfig;
import br.com.candalo.popularmovies.base.data.CloudDataSource;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReviewResponseData;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.Observable;

public class MovieReviewsCloudDataSource implements CloudDataSource<List<MovieReview>, Integer> {

    private Context context;
    private MovieApi movieApi;

    @Inject
    public MovieReviewsCloudDataSource(Context context, MovieApi movieApi) {
        this.context = context;
        this.movieApi = movieApi;
    }

    @Override
    public Observable<List<MovieReview>> call(Integer movieId) {
        return ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMap(connectivity -> {
                    if(connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        return movieApi
                                .getReviews(movieId, BuildConfig.ApiKey)
                                .map(MovieReviewResponseData::getMovieReviews);
                    }
                    return Observable.error(new NetworkException());
                });
    }
}
