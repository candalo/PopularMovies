package br.com.candalo.popularmovies.base.data;


import java.util.List;

import io.reactivex.Observable;

public interface LocalDataSource<T> {

    Observable<Void> save(T param);

    Observable<T> find(int id);

    Observable<List<T>> list();

}
