package br.com.candalo.popularmovies.features.movies.data.di;

import java.util.List;

import br.com.candalo.popularmovies.base.data.di.ActivityScope;
import br.com.candalo.popularmovies.base.domain.UseCase;
import br.com.candalo.popularmovies.features.movies.data.datasource.MovieApi;
import br.com.candalo.popularmovies.features.movies.data.datasource.MovieQuerySpecImpl;
import br.com.candalo.popularmovies.features.movies.data.repository.MovieRepositoryImpl;
import br.com.candalo.popularmovies.features.movies.domain.datasource.MovieQuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.repository.MovieRepository;
import br.com.candalo.popularmovies.features.movies.domain.usecases.GetMovieListByPopularity;
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
    @ActivityScope
    MovieQuerySpec provideMovieQuerySpec(MovieApi movieApi) {
        return new MovieQuerySpecImpl(movieApi);
    }

    @Provides
    @ActivityScope
    MovieRepository provideMovieRepository() {
        return new MovieRepositoryImpl();
    }

    @Provides
    @ActivityScope
    UseCase<List<Movie>, Void> provideGetMovieListByPopularityUseCase(MovieRepository movieRepository,
                                                                      MovieQuerySpec movieQuerySpec) {
        return new GetMovieListByPopularity(movieRepository, movieQuerySpec);
    }

    @Provides
    @ActivityScope
    MoviePresenter provideMoviePresenter(UseCase<List<Movie>, Void> getMovieListByPopularityUseCase) {
        return new MoviePresenter(getMovieListByPopularityUseCase);
    }
}
