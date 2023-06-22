package com.example.movieapp.data.service;

import com.example.movieapp.data.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIService {

    @GET("/saniyusuf/406b843afdfb9c6a86e25753fe2761f4/raw/523c324c7fcc36efab8224f9ebb7556c09b69a14/Film.JSON")
    Call<ArrayList<Movie>> getMovies();
}
