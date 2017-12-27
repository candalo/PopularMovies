package br.com.candalo.popularmovies.features.movies.presentation.view;


import java.util.List;

import br.com.candalo.popularmovies.base.presentation.LoadDataView;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;

public interface MovieView extends LoadDataView {

    void onMoviesLoaded(List<Movie> movies);

    void onMovieSelected(Movie movie);
}
