package br.com.candalo.popularmovies.features.movies.domain.repository;


import java.util.List;

import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import io.reactivex.Observable;

public interface MovieTrailerRepository {

    Observable<List<Video>> listByMovieId(int movieId);

}
