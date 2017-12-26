package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

public class Movie {

    private String title;
    @SerializedName("backdrop_path")
    private String posterThumbnail;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("release_date")
    private String releaseDate;

    public Movie(String title, String posterThumbnail, String synopsis, String releaseDate) {
        this.title = title;
        this.posterThumbnail = posterThumbnail;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
