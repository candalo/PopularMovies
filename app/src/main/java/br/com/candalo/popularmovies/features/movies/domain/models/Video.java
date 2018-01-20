package br.com.candalo.popularmovies.features.movies.domain.models;


public class Video {

    private String key;
    private String name;
    private String site;
    private String type;

    public Video(String key, String name, String site, String type) {
        this.key = key;
        this.name = name;
        this.site = site;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public enum Properties {
        SITE("YouTube"), TYPE("Trailer");

        private final String property;

        Properties(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }
}
