package br.com.candalo.popularmovies.base.domain;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public interface UseCase<T, Params> {

    <S extends Observer<T> & Disposable> void execute(S observer, Params params);

    void dispose();
}
