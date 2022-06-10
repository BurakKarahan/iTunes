package com.example.itunes.Model;

public class MusicResult {

    public String artistName;
    public String trackName;
    public String artistViewUrl;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl100;
    public String trackPrice;
    public String trackTimeMillis;
    public String releaseDate;
    public String primaryGenreName;

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

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    @Override
    public String toString() {
        return "MusicResult{" +
                "artistName='" + artistName + '\'' +
                ", trackName='" + trackName + '\'' +
                ", artistViewUrl='" + artistViewUrl + '\'' +
                ", trackViewUrl='" + trackViewUrl + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", trackPrice='" + trackPrice + '\'' +
                ", trackTimeMillis='" + trackTimeMillis + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", primaryGenreName='" + primaryGenreName + '\'' +
                '}';
    }
}
