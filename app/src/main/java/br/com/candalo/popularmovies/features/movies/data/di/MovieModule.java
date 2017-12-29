package br.com.candalo.popularmovies.features.movies.data.di;

import android.content.Context;

import java.util.List;

import javax.inject.Named;

import br.com.candalo.popularmovies.base.data.di.ActivityScope;
import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.base.presentation.ErrorHandler;
import br.com.candalo.popularmovies.features.movies.data.datasource.MovieApi;
import br.com.candalo.popularmovies.features.movies.data.datasource.MoviesByPopularityQuerySpec;
import br.com.candalo.popularmovies.features.movies.data.datasource.MoviesByRatingQuerySpec;
import br.com.candalo.popularmovies.features.movies.data.repository.MovieRepositoryImpl;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import br.com.candalo.popularmovies.features.movies.domain.usecases.GetMovieListByPopularity;
import br.com.candalo.popularmovies.features.movies.domain.usecases.GetMovieListByRating;
import br.com.candalo.popularmovies.features.movies.presentation.error.MovieErrorHandler;
import br.com.candalo.popularmovies.features.movies.presentation.presenter.MoviePresenter;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieModule {

    @Provides
    @ActivityScope
    MovieApi provideMovieApi(Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }

    @Provides
    @Named("movies_by_popularity_query_spec")
    @ActivityScope
    MovieQuerySpec provideMoviesByPopularityQuerySpec(Context context, MovieApi movieApi) {
        return new MoviesByPopularityQuerySpec(context, movieApi);
    }

    @Provides
    @Named("movies_by_rating_query_spec")
    @ActivityScope
    MovieQuerySpec provideMoviesByRatingQuerySpec(Context context, MovieApi movieApi) {
        return new MoviesByRatingQuerySpec(context, movieApi);
    }

    @Provides
    @ActivityScope
    MovieRepository provideMovieRepository() {
        return new MovieRepositoryImpl();
    }

    @Provides
    @Named("get_movie_list_by_popularity")
    @ActivityScope
    UseCase<List<Movie>, Void>
    provideGetMovieListByPopularityUseCase(MovieRepository movieRepository,
                                           @Named("movies_by_popularity_query_spec") MovieQuerySpec movieQuerySpec) {
        return new GetMovieListByPopularity(movieRepository, movieQuerySpec);
    }

    @Provides
    @Named("get_movie_list_by_rating")
    @ActivityScope
    UseCase<List<Movie>, Void>
    provideGetMovieListByRatingUseCase(MovieRepository movieRepository,
                                       @Named("movies_by_rating_query_spec") MovieQuerySpec movieQuerySpec) {
        return new GetMovieListByRating(movieRepository, movieQuerySpec);
    }

    @Provides
    @ActivityScope
    ErrorHandler provideMovieErrorHandler(Context context) {
        return new MovieErrorHandler(context);
    }

    @Provides
    @ActivityScope
    MoviePresenter provideMoviePresenter(@Named("get_movie_list_by_popularity") UseCase<List<Movie>, Void> getMovieListByPopularityUseCase,
                                         @Named("get_movie_list_by_rating") UseCase<List<Movie>, Void> getMovieListByRatingUseCase,
                                         ErrorHandler errorHandler) {
        return new MoviePresenter(getMovieListByPopularityUseCase, getMovieListByRatingUseCase, errorHandler);
    }
}
