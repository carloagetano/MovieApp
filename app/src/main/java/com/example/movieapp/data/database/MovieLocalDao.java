package com.example.movieapp.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movieapp.data.database.model.MovieLocal;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieLocalDao {

    @Query("SELECT * FROM movie_local")
    List<MovieLocal> getAll();

    @Insert
    void insert(MovieLocal... movieLocal);

    @Query("SELECT id FROM movie_local LIMIT 1")
    int checkData();

}
