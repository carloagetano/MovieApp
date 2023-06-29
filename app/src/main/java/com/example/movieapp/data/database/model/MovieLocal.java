package com.example.movieapp.data.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "movie_local")
public class MovieLocal implements Serializable {
    public MovieLocal(String title, String year, String rated, String released, String runtime,
                      String genre, String director, String writer, String actors, String plot,
                      String language, String country, String awards, String poster, String metascore,
                      String imdbRating, String imdbVotes, String imdbID, String type, String response,
                      String images, String totalSeasons, Boolean comingSoon) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.type = type;
        this.response = response;
        this.images = images;
        this.totalSeasons = totalSeasons;
        this.comingSoon = comingSoon;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "rated")
    public String rated;

    @ColumnInfo(name = "released")
    public String released;

    @ColumnInfo(name = "runtime")
    public String runtime;

    @ColumnInfo(name = "genre")
    public String genre;

    @ColumnInfo(name = "director")
    public String director;

    @ColumnInfo(name = "writer")
    public String writer;

    @ColumnInfo(name = "actors")
    public String actors;

    @ColumnInfo(name = "plot")
    public String plot;

    @ColumnInfo(name = "language")
    public String language;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "awards")
    public String awards;

    @ColumnInfo(name = "poster")
    public String poster;

    @ColumnInfo(name = "metascore")
    public String metascore;

    @ColumnInfo(name = "imdb_rating")
    public String imdbRating;

    @ColumnInfo(name = "imdb_votes")
    public String imdbVotes;

    @ColumnInfo(name = "imdb_id")
    public String imdbID;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "response")
    public String response;

    @ColumnInfo(name = "images")
    public String images;

    @ColumnInfo(name = "total_seasons")
    public String totalSeasons;

    @ColumnInfo(name = "coming_soon")
    public Boolean comingSoon;
}
