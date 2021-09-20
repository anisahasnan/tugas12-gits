package com.example.tugas12_anisahasna.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas12_anisahasna.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView backdrop;
    private TextView title, date, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String backdropPath = getIntent().getStringExtra("backdrop");
        String titleTxt = getIntent().getStringExtra("title");
        String dateTxt = getIntent().getStringExtra("date");
        String detail = getIntent().getStringExtra("detail");

        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        overview = findViewById(R.id.overview);

        Glide.with(DetailActivity.this).load(backdropPath)
                .into(backdrop);

        title.setText(titleTxt);
        date.setText(dateTxt);
        overview.setText(detail);
    }
}