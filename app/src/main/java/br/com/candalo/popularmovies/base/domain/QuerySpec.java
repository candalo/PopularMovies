package br.com.candalo.popularmovies.base.domain;


import io.reactivex.Observable;

public interface QuerySpec<T> {

    Observable<T> query();

}
