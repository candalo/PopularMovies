package br.com.candalo.popularmovies.features.movies.data.di;

import br.com.candalo.popularmovies.base.data.di.ActivityScope;
import br.com.candalo.popularmovies.base.data.di.ApplicationComponent;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieActivity;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieDetailsActivity;
import dagger.Component;

@Component(dependencies = ApplicationComponent.class, modules = MovieModule.class)
@ActivityScope
public interface MovieComponent {

    void inject(MovieActivity moviesActivity);
    void inject(MovieDetailsActivity movieDetailsActivity);

}
