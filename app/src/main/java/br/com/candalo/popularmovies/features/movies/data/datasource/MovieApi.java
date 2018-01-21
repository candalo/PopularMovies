package br.com.candalo.popularmovies.features.movies.data.datasource;


import br.com.candalo.popularmovies.features.movies.domain.models.MovieResponseData;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieVideosResponseData;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReviewResponseData;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/movie/popular")
    Observable<MovieResponseData> getMostPopularMovies(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Observable<MovieResponseData> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("3/movie/{id}/videos")
    Observable<MovieVideosResponseData> getTrailers(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("3/movie/{id}/reviews")
    Observable<MovieReviewResponseData> getReviews(@Path("id") int movieId, @Query("api_key") String apiKey);
}
