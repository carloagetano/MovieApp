package com.example.movieapp.presentation.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.data.database.model.MovieLocal;
import com.example.movieapp.data.repository.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application.getApplicationContext());

        saveMovies();
    }

    private void saveMovies() {
        movieRepository.saveMovies(success -> {
            if (success) {
                getAllMovies();
            }
        });
    }

    public LiveData<ArrayList<MovieLocal>> getAllMovies() {
        return movieRepository.getMoviesLiveData();
    }

    /*public LiveData<ArrayList<Movie>> getAllMovies() {
        return movieRepository.getMoviesLiveData();
    }*/


}
