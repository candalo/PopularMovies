package br.com.candalo.popularmovies.features.movies.domain.models;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class Movie {

    private String title;
    @SerializedName("poster_path")
    private String posterThumbnail;
    @SerializedName("backdrop_path")
    private String backdropThumbnail;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("vote_average")
    private double userAverage;
    @SerializedName("release_date")
    private String releaseDate;

    @ParcelConstructor
    public Movie(String title,
                 String posterThumbnail,
                 String backdropThumbnail,
                 String synopsis,
                 double userAverage,
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

    public double getUserAverage() {
        return userAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
