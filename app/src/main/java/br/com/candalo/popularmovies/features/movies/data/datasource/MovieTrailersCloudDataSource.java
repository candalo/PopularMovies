package br.com.candalo.popularmovies.features.movies.data.datasource;


import android.content.Context;
import android.net.NetworkInfo;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.BuildConfig;
import br.com.candalo.popularmovies.base.data.CloudDataSource;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieVideosResponseData;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.network.NetworkException;
import io.reactivex.Observable;

public class MovieTrailersCloudDataSource implements CloudDataSource<List<Video>, Integer> {

    private Context context;
    private MovieApi movieApi;

    @Inject
    public MovieTrailersCloudDataSource(Context context, MovieApi movieApi) {
        this.context = context;
        this.movieApi = movieApi;
    }

    @Override
    public Observable<List<Video>> call(Integer movieId) {
        return ReactiveNetwork
                .observeNetworkConnectivity(context)
                .flatMap(connectivity -> {
                    if(connectivity.getState() == NetworkInfo.State.CONNECTED) {
                        return movieApi
                                .getTrailers(movieId, BuildConfig.ApiKey)
                                .map(MovieVideosResponseData::getVideos);
                    }
                    return Observable.error(new NetworkException());
                });
    }
}
