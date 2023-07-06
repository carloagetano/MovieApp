package com.example.movieapp.presentation.main.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityAddMovieBinding;
import com.example.movieapp.presentation.main.viewmodel.AddMovieViewModel;

public class AddMovieActivity extends AppCompatActivity {

    private ActivityAddMovieBinding binding;
    private AddMovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie);
        viewModel = new ViewModelProvider(this).get(AddMovieViewModel.class);

        subscribe();
        onClickListeners();
    }

    public void subscribe() {
        viewModel.isMovieAdded().observe(this, it -> {
            if (it) {
                clearFields();
            } else {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickListeners() {
        binding.backBtn.setOnClickListener(v -> {
            finish();
        });

        binding.addMovieBtn.setOnClickListener(v -> {
            String title = binding.titleEtv.getText().toString();
            String released = binding.releasedEtv.getText().toString();
            String rating = binding.ratingEtv.getText().toString();
            String imgPoster = binding.imgPosterEtv.getText().toString();
            String imgBackdrop = binding.imgBackdropEtv.getText().toString();
            String plot = binding.plotEtv.getText().toString();

            if (!title.isEmpty() && !released.isEmpty() && !rating.isEmpty() && !imgPoster.isEmpty()
                    && !imgBackdrop.isEmpty() && !plot.isEmpty()) {
                viewModel.addMovie(title, released, rating, imgPoster, imgBackdrop, plot);

            } else {
                Toast.makeText(this, "Please complete all fields.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void clearFields() {
        binding.titleEtv.setText("");
        binding.releasedEtv.setText("");
        binding.ratingEtv.setText("");
        binding.imgPosterEtv.setText("");
        binding.imgBackdropEtv.setText("");
        binding.plotEtv.setText("");

        Toast.makeText(this, "Movie added successfully!", Toast.LENGTH_LONG).show();
    }
}