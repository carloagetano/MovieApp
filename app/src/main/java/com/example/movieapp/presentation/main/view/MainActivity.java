package com.example.movieapp.presentation.main.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.data.database.model.MovieLocal;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.presentation.main.adapter.MoviesAdapter;
import com.example.movieapp.presentation.main.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        subscribe();
    }

    public void subscribe() {
        viewModel.getAllMovies().observe(this, this::loadRv);
    }

    public void loadRv(ArrayList<MovieLocal> movies) {
        //for dummy data
        /*ArrayList<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie("Avatar", "2009", "7.9", "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg");
        Movie movie2 = new Movie("I Am Legend", "2007", "7.2", "Years after a plague kills most of humanity and transforms the rest into monsters, the sole survivor in New York City struggles valiantly to find a cure.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMTI0NTI4NjE3NV5BMl5BanBnXkFtZTYwMDA0Nzc4._V1_.jpg");
        Movie movie3 = new Movie("The Avengers", "2012", "8.1", "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMTA0NjY0NzE4OTReQTJeQWpwZ15BbWU3MDczODg2Nzc@._V1_SX1777_CR0,0,1777,999_AL_.jpg");
        Movie movie4 = new Movie("The Wolf of Wall Street", "2013", "8.2", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNDIwMDIxNzk3Ml5BMl5BanBnXkFtZTgwMTg0MzQ4MDE@._V1_SX1500_CR0,0,1500,999_AL_.jpg");
        Movie movie5 = new Movie("Interstellar", "2014", "8.6", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjA3NTEwOTMxMV5BMl5BanBnXkFtZTgwMjMyODgxMzE@._V1_SX1500_CR0,0,1500,999_AL_.jpg");
        Movie movie6 = new Movie("Game of Thrones", "2011", "9.5", "While a civil war brews between several noble families in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile a forgotten race, bent on destruction, plans to return after thousands of years in the North.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNDc1MGUyNzItNWRkOC00MjM1LWJjNjMtZTZlYWIxMGRmYzVlXkEyXkFqcGdeQXVyMzU3MDEyNjk@._V1_SX1777_CR0,0,1777,999_AL_.jpg");
        Movie movie7 = new Movie("Vikings", "2013", "8.6", "The world of the Vikings is brought to life through the journey of Ragnar Lothbrok, the first Viking to emerge from Norse legend and onto the pages of history - a man on the edge of myth.",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMjM5MTM1ODUxNV5BMl5BanBnXkFtZTgwNTAzOTI2ODE@._V1_.jpg");

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);
        movies.add(movie7);*/

        binding.movieRv.setLayoutManager(new LinearLayoutManager(this));
        binding.movieRv.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter();
        binding.movieRv.setAdapter(moviesAdapter);

        moviesAdapter.setMovies(movies);

        //binding.setMovie(movies.get(0));

        moviesAdapter.setListener(new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieLocal movie) {

                int orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    binding.setMovie(movie);

                    //set movie poster
                    Glide.with(binding.getRoot())
                            .load(movie.images)
                            .centerCrop()
                            .into(binding.moviePosterImg);

                    //set movie bg
                    Glide.with(binding.getRoot())
                            .load(movie.poster)
                            .centerCrop()
                            .into(binding.movieBgPosterImg);
                } else {
                    Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                    intent.putExtra(MovieDetailsActivity.MOVIE, movie);
                    startActivity(intent);
                }
            }
        });

    }
}