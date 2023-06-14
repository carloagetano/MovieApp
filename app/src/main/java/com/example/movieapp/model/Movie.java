package com.example.movieapp.model;

public class Movie {
    private String title;
    private String year;
    private float rating;
    private String overview;
    //private int moviePosterImg;

    public Movie(String title, String year, float rating, String overview) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.overview = overview;
        //this.moviePosterImg = moviePosterImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    /*public int getMoviePosterImg() {
        return moviePosterImg;
    }

    public void setMoviePosterImg(int moviePosterImg) {
        this.moviePosterImg = moviePosterImg;
    }*/
}
