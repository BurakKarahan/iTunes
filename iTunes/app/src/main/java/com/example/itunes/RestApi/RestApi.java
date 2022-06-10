package com.example.itunes.RestApi;

import com.example.itunes.Model.BookRoot;
import com.example.itunes.Model.MovieRoot;
import com.example.itunes.Model.MusicRoot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/search")
    Call<MusicRoot> RestMusic(@Field("country") String sCountry, @Field("term") String sTerm, @Field("media") String sMedia);

    @FormUrlEncoded
    @POST("/search")
    Call<MovieRoot> RestMovie(@Field("country") String sCountry, @Field("term") String sTerm, @Field("media") String sMedia);

    @FormUrlEncoded
    @POST("/search")
    Call<BookRoot> RestBook(@Field("country") String sCountry, @Field("term") String sTerm, @Field("media") String sMedia);


}
