package com.example.tugas12_anisahasna.activity;

import static com.example.tugas12_anisahasna.App.db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.adapter.AddFavMovAdapter;
import com.example.tugas12_anisahasna.api.ApiService;
import com.example.tugas12_anisahasna.api.InitRetrofit;
import com.example.tugas12_anisahasna.database.Movie;
import com.example.tugas12_anisahasna.response.MovieResponse;
import com.example.tugas12_anisahasna.response.ResultsItem;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFavMovieActivity extends AppCompatActivity {

    private EditText formTitle, formGenre, formOverview;
    private Button submitBtn;

    Movie movie;

    RecyclerView recyclerView;
    AddFavMovAdapter afmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fav_movie);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        recyclerView = findViewById(R.id.rcvAddFavMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        loadMovieNow();

    }

    private void loadMovieNow() {

        ApiService apiService = InitRetrofit.getInstance();
        Call<MovieResponse> responseCall = apiService.now_playing();
        responseCall.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {

                if (response.isSuccessful()) {
                    Log.i("movie", Objects.requireNonNull(response.body()).getResults().toString());
                    ArrayList<ResultsItem> resultsItems = (ArrayList<ResultsItem>) response.body().getResults();
                    afmAdapter = new AddFavMovAdapter(AddFavMovieActivity.this, resultsItems);
                    recyclerView.setAdapter(afmAdapter);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

                t.printStackTrace();
            }

        });
    };
}