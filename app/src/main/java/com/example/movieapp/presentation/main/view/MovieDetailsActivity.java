package com.example.movieapp.presentation.main.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.data.model.Movie;
import com.example.movieapp.databinding.ActivityMovieDetailsBinding;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String MOVIE = "movie";
    private ActivityMovieDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(MOVIE);

        setMovieData(movie);

        binding.movieDetailTitleTv.setOnClickListener(v -> {
            startActivity(new Intent(MovieDetailsActivity.this, MainActivity.class));
        });
    }

    public void setMovieData(Movie movie) {
        binding.setMovie(movie);

        //set movie poster
        Glide.with(this)
                .load(movie.getImages().get(0))
                .centerCrop()
                .into(binding.moviePosterImg);

        //set movie bg
        Glide.with(this)
                .load(movie.getImages().get(1))
                .centerCrop()
                .into(binding.movieBgPosterImg);
    }
}