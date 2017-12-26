package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponseData {

    @SerializedName("results")
    private List<Movie> movies;

    public MovieResponseData(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
