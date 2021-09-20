package com.example.tugas12_anisahasna.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas12_anisahasna.R;

public class FavDetailActivity extends AppCompatActivity {

    private TextView title, genre, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_detail);

        String titleTxt = getIntent().getStringExtra("title");
        String genreTxt = getIntent().getStringExtra("genre");
        String detail = getIntent().getStringExtra("detail");

        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        overview = findViewById(R.id.overview);

        title.setText(titleTxt);
        genre.setText(genreTxt);
        overview.setText(detail);
    }
}