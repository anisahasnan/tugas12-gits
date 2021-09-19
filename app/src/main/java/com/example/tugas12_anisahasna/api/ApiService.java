package com.example.tugas12_anisahasna.api;

import com.example.tugas12_anisahasna.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie/now_playing?api_key=ae204e2a691d1df95f9416780252f706")
    Call<MovieResponse> now_playing();
}
