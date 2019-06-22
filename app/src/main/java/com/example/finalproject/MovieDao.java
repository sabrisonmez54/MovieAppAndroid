package com.example.finalproject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao
{
    @Insert
    void insert(MovieItem movieItem);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * from movie_table")
    LiveData<List<MovieItem>> getAllMovies();
}
