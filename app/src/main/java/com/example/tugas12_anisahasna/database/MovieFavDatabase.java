package com.example.tugas12_anisahasna.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieFavDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
