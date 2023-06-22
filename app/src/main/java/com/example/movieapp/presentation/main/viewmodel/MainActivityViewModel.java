package com.example.movieapp.presentation.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.repository.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
    }

    public LiveData<ArrayList<Movie>> getAllMovies() {
        return movieRepository.getMoviesLiveData();
    }



}
