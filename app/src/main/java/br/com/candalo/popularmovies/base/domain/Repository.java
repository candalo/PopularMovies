package br.com.candalo.popularmovies.base.domain;


import java.util.List;

import io.reactivex.Observable;

public interface Repository<T> {

    Observable<List<T>> listByMovieId(int movieId);

}
