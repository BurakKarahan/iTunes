package com.example.itunes.Model;

import java.util.ArrayList;
import java.util.List;

public class BookResult {

    public String artworkUrl100;
    public String currency;
    public String trackName;
    public String description;
    public String artistName;
    public String price;
    public String averageUserRating;
    public String userRatingCount;
    public String formattedPrice;
    public ArrayList<String> genres;

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAverageUserRating() {
        return averageUserRating;
    }

    public void setAverageUserRating(String averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    public String getUserRatingCount() {
        return userRatingCount;
    }

    public void setUserRatingCount(String userRatingCount) {
        this.userRatingCount = userRatingCount;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "BookResult{" +
                "artworkUrl100='" + artworkUrl100 + '\'' +
                ", currency='" + currency + '\'' +
                ", trackName='" + trackName + '\'' +
                ", description='" + description + '\'' +
                ", artistName='" + artistName + '\'' +
                ", price='" + price + '\'' +
                ", averageUserRating='" + averageUserRating + '\'' +
                ", userRatingCount='" + userRatingCount + '\'' +
                ", formattedPrice='" + formattedPrice + '\'' +
                ", genres=" + genres +
                '}';
    }
}
