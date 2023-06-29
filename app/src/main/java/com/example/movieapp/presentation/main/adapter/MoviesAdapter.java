package com.example.movieapp.presentation.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.data.database.model.MovieLocal;
import com.example.movieapp.databinding.ItemMovieBinding;
import com.example.movieapp.data.model.Movie;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<MovieLocal> movieList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemMovieBinding itemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieLocal movie = movieList.get(position);
        holder.itemMovieBinding.setMovie(movie);

        Glide.with(holder.itemMovieBinding.getRoot())
                .load(movie.images)
                .centerCrop()
                .into(holder.itemMovieBinding.moviePosterImg);

    }

    public void setMovies(ArrayList<MovieLocal> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding itemMovieBinding;

        public MovieViewHolder(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;

            itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();

                    if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemMovieBinding.getMovie());
                    }

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(MovieLocal movie);
    }

    public void setListener(OnItemClickListener listener) { this.listener = listener;}
}
