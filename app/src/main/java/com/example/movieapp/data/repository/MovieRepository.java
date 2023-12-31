package com.example.movieapp.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.movieapp.data.database.AppDatabase;
import com.example.movieapp.data.database.MovieLocalDao;
import com.example.movieapp.data.database.model.MovieLocal;
import com.example.movieapp.data.model.Movie;
import com.example.movieapp.data.service.ApiCallback;
import com.example.movieapp.data.service.MovieAPIService;
import com.example.movieapp.data.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    public MovieRepository(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,
                AppDatabase.class, "movie_local").build();

        movieDao = db.movieLocalDao();
    }

    private ArrayList<Movie> movies = new ArrayList<>();
    //private MutableLiveData<ArrayList<Movie>> moviesLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieLocal>> moviesLiveData = new MutableLiveData<>();
    private MovieLocalDao movieDao;

    private Context context;

    private AppDatabase db;

    private final String authToken = "dGVzdDpzZWNyZXQ=";

    private Movie addedMovie;

    public void saveMovies(final ApiCallback callback) {
        MovieAPIService movieAPIService = RetrofitInstance.getService();

        Call<ArrayList<Movie>> call = movieAPIService.getMovies();

        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                movies = response.body();

                AppDatabase.databaseWriteExecutor.execute(() -> {
                    if (movieDao.checkData() == 1) {
                        callback.onResponse(true);
                        return;
                    }
                    if (movies != null) {
                        for (Movie movie : movies) {
                            movieDao.insert(new MovieLocal(movie.getTitle(), movie.getYear(),
                                    movie.getRated(), movie.getReleased(), movie.getRuntime(),
                                    movie.getGenre(), movie.getDirector(), movie.getWriter(),
                                    movie.getActors(), movie.getPlot(), movie.getLanguage(), movie.getCountry(),
                                    movie.getAwards(), movie.getImages().get(1), movie.getMetascore(),
                                    movie.getImdbRating(), movie.getImdbVotes(), movie.getImdbID(),
                                    movie.getType(), movie.getResponse(), movie.getImages().get(0),
                                    movie.getTotalSeasons(), movie.getComingSoon()));
                        }
                    }
                    callback.onResponse(true);
                });
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                callback.onResponse(false);
            }
        });
    }

    public MutableLiveData<ArrayList<MovieLocal>> getMoviesLiveData() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ArrayList<MovieLocal> convertedMovie = new ArrayList<>(movieDao.getAll());
            moviesLiveData.postValue(convertedMovie);
        });

        return moviesLiveData;
    }

    //post api to add a movie
    public void addMovie(Movie movie, final ApiCallback callback) {
        MovieAPIService movieAPIService = RetrofitInstance.getService();

        Call<Movie> call = movieAPIService.addMovie(movie, authToken);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                addedMovie = response.body();

                AppDatabase.databaseWriteExecutor.execute(() -> {
                    if (addedMovie != null) {
                        movieDao.insert(new MovieLocal(addedMovie.getTitle(), addedMovie.getYear(),
                                addedMovie.getRated(), addedMovie.getReleased(), addedMovie.getRuntime(),
                                addedMovie.getGenre(), addedMovie.getDirector(), addedMovie.getWriter(),
                                addedMovie.getActors(), addedMovie.getPlot(), addedMovie.getLanguage(), addedMovie.getCountry(),
                                addedMovie.getAwards(), addedMovie.getImages().get(1), addedMovie.getMetascore(),
                                addedMovie.getImdbRating(), addedMovie.getImdbVotes(), addedMovie.getImdbID(),
                                addedMovie.getType(), addedMovie.getResponse(), addedMovie.getImages().get(0),
                                addedMovie.getTotalSeasons(), addedMovie.getComingSoon()));
                    }
                    callback.onResponse(true);
                    /*Log.d("Movie Status", "Successfully added movie: " + addedMovie.getTitle()
                            + ", Released date: " + addedMovie.getReleased());*/
                });
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onResponse(false);
                Log.d("Movie Status", "Error found is " + t.getMessage());
            }
        });
    }

    //retrofit api
    /*public MutableLiveData<ArrayList<Movie>> getMoviesLiveData() {

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
    }*/
}