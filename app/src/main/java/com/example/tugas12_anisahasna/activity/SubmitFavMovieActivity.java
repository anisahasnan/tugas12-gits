package com.example.tugas12_anisahasna.activity;

import static com.example.tugas12_anisahasna.App.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.database.Movie;

public class SubmitFavMovieActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String backdropPath = getIntent().getStringExtra("backdrop");
        String posterPath = getIntent().getStringExtra("poster");
        String titleTxt = getIntent().getStringExtra("title");
        String dateTxt = getIntent().getStringExtra("date");
        String detail = getIntent().getStringExtra("detail");

        movie = new Movie();
        movie.setTitle(titleTxt);
        movie.setBackdropImg(backdropPath);
        movie.setPosterImg(posterPath);
        movie.setOverview(detail);
        movie.setDate(dateTxt);
        db.movieDao().insert(movie);
        Toast.makeText(SubmitFavMovieActivity.this, "Berhasil menambah film favorit", Toast.LENGTH_SHORT).show();
    }
}