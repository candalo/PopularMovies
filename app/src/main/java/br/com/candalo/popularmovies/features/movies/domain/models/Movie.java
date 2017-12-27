package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

public class Movie {

    private String title;
    @SerializedName("poster_path")
    private String posterThumbnail;
    @SerializedName("backdrop_path")
    private String backdropThumbnail;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("vote_average")
    private String userAverage;
    @SerializedName("release_date")
    private String releaseDate;

    public Movie(String title,
                 String posterThumbnail,
                 String backdropThumbnail,
                 String synopsis,
                 String userAverage,
                 String releaseDate) {
        this.title = title;
        this.posterThumbnail = posterThumbnail;
        this.backdropThumbnail = backdropThumbnail;
        this.synopsis = synopsis;
        this.userAverage = userAverage;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterThumbnail() {
        return posterThumbnail;
    }

    public String getBackdropThumbnail() {
        return backdropThumbnail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getUserAverage() {
        return userAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
