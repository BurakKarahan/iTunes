package com.example.itunes.Model;

public class MovieResult {

    public String artistName;
    public String trackName;
    public String previewUrl;
    public String artworkUrl100;
    public String trackPrice;
    public String trackTimeMillis;
    public String primaryGenreName;
    public String contentAdvisoryRating;
    public String longDescription;
    public String releaseDate;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(String trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieResult{" +
                "artistName='" + artistName + '\'' +
                ", trackName='" + trackName + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", trackPrice='" + trackPrice + '\'' +
                ", trackTimeMillis='" + trackTimeMillis + '\'' +
                ", primaryGenreName='" + primaryGenreName + '\'' +
                ", contentAdvisoryRating='" + contentAdvisoryRating + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
