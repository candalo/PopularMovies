package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReviewResponseData {

    @SerializedName("results")
    private List<MovieReview> movieReviews;

    public MovieReviewResponseData(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    public List<MovieReview> getMovieReviews() {
        return movieReviews;
    }
}
