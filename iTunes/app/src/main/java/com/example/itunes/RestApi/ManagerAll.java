package com.example.itunes.RestApi;

import com.example.itunes.Model.BookRoot;
import com.example.itunes.Model.MovieRoot;
import com.example.itunes.Model.MusicRoot;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll Web = new ManagerAll();
    public static synchronized ManagerAll Web() {
        return Web;
    }

    public Call<MusicRoot> ManagerMusic(String sCountry, String sTerm, String sMedia) {
        Call<MusicRoot> ManagerMusic = getRestApiClient().RestMusic(sCountry, sTerm, sMedia);
        return ManagerMusic;
    }

    public Call<MovieRoot> ManagerMovie(String sCountry, String sTerm, String sMedia) {
        Call<MovieRoot> ManagerMovie = getRestApiClient().RestMovie(sCountry, sTerm, sMedia);
        return ManagerMovie;
    }

    public Call<BookRoot> ManagerBook(String sCountry, String sTerm, String sMedia) {
        Call<BookRoot> ManagerBook = getRestApiClient().RestBook(sCountry, sTerm, sMedia);
        return ManagerBook;
    }
}
