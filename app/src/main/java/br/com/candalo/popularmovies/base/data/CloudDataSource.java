package br.com.candalo.popularmovies.base.data;


import io.reactivex.Observable;

public interface CloudDataSource<T, PARAMS> {

    Observable<T> call(PARAMS params);

}
