package br.com.candalo.popularmovies.features.movies.presentation.view;


import java.util.List;

import br.com.candalo.popularmovies.base.presentation.LoadDataView;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;

public interface MovieDetailsView extends LoadDataView {

    void onTrailersLoaded(List<Video> trailers);

    void onTrailerItemClicked(Video trailer);

    void onReviewsLoaded(List<MovieReview> reviews);

    void onReviewItemClicked(MovieReview review);

    void showStarred();

    void showUnstarred();
}
