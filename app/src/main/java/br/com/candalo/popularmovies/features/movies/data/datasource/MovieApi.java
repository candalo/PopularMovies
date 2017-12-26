package br.com.candalo.popularmovies.features.movies.data.datasource;


import br.com.candalo.popularmovies.features.movies.domain.models.MovieResponseData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/movie/top_rated")
    Observable<MovieResponseData> getTopRatedMovies(@Query("api_key") String apiKey);
}
