package com.example.movieapp.data.service;

import com.example.movieapp.data.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MovieAPIService {

    @GET("/movies")
    Call<ArrayList<Movie>> getMovies();

    @POST("/add-movie")
    Call<Movie> addMovie(@Body Movie movie, @Header("Authorization") String authorization);
}
