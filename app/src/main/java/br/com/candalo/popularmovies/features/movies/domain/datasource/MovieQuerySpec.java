package br.com.candalo.popularmovies.features.movies.domain.datasource;


import java.util.List;

import br.com.candalo.popularmovies.base.domain.QuerySpec;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;

public interface MovieQuerySpec extends QuerySpec<List<Movie>> {
}
