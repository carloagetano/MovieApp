package com.example.movieapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.service.MovieAPIService;
import com.example.movieapp.data.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<ArrayList<Movie>> moviesLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Movie>> getMoviesLiveData() {
        MovieAPIService movieAPIService = RetrofitInstance.getService();

        Call<ArrayList<Movie>> call = movieAPIService.getMovies();

        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                movies = response.body();

                if (movies != null) {
                    moviesLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {

            }
        });

        return moviesLiveData;
    }
}