package br.com.candalo.popularmovies.features.movies.presentation.view;


import java.util.List;

import br.com.candalo.popularmovies.base.presentation.LoadDataView;

public interface MovieDetailsView extends LoadDataView {

    void onTrailerUrlsLoaded(List<String> urls);

}
