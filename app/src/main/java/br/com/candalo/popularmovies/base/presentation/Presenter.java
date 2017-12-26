package br.com.candalo.popularmovies.base.presentation;


public interface Presenter<T> {

    void attachTo(T view);

    void destroy();

}
