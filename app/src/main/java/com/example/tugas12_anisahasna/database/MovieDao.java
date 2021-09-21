package com.example.tugas12_anisahasna.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.Optional;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Query("SELECT * FROM movie")
    List<Movie> getAllMovie();

    @Query("DELETE FROM movie")
    void deleteAllMovie();

    @Query("SELECT * FROM movie WHERE title = :title")
    Movie findMovieByTitle(String title);

}
