package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideosResponseData {

    @SerializedName("results")
    private List<Video> videos;

    public MovieVideosResponseData(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
