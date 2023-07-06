package com.example.movieapp.presentation.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class AddMovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    private MutableLiveData<Boolean> _isMovieAdded = new MutableLiveData<>();

    public LiveData<Boolean> isMovieAdded() {
        return _isMovieAdded;
    }

    public AddMovieViewModel(@NonNull Application application) {
        super(application);

        movieRepository = new MovieRepository(application.getApplicationContext());
    }

    public void addMovie(String title, String released, String rating, String poster,
                         String backdrop, String plot) {

        List<String> images = new ArrayList<>();
        images.add(poster);
        images.add(backdrop);

        Movie newMovie = new Movie(title, "", "", released, "", "",
                "", "", "", plot, "", "", "",
                "", "", rating, "", "", "",
                "", images, "", false);

        movieRepository.addMovie(newMovie, success -> {
            if (success) {
                //movie added successfully
                _isMovieAdded.postValue(true);
            } else {
                //movie failed
                _isMovieAdded.postValue(false);
            }
        });

    }
}
