package com.example.tugas12_anisahasna.activity;

import static com.example.tugas12_anisahasna.App.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas12_anisahasna.DateHelper;
import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.database.Movie;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class FavMovieAddActivity extends AppCompatActivity {

    private EditText formTitle, formGenre, formOverview;
    private Button submitBtn;

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie_add);
        initView();

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        movie = new Movie();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie.setTitle(formTitle.getText().toString().trim());
                movie.setGenre(formGenre.getText().toString().trim());
                movie.setOverview(formOverview.getText().toString().trim());
                movie.setDateFav(DateHelper.getCurrentDate());
                db.movieDao().insert(movie);
                Toast.makeText(FavMovieAddActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        formTitle = findViewById(R.id.formTitle);
        formGenre = findViewById(R.id.formGenre);
        formOverview = findViewById(R.id.formOverview);
        submitBtn = findViewById(R.id.btn_submit);
    }
}