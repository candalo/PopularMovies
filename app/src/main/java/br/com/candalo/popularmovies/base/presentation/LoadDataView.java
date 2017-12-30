package br.com.candalo.popularmovies.base.presentation;


public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showErrorMessage(String errorMessage);

    void showNetworkErrorMessage();
}
