package com.example.movieapp.data.service;

import com.example.movieapp.data.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIService {

    @GET("/movies")
    Call<ArrayList<Movie>> getMovies();
}
