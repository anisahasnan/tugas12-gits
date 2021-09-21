package com.example.tugas12_anisahasna.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.activity.DetailActivity;
import com.example.tugas12_anisahasna.database.Movie;

import java.util.ArrayList;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.FavMovieHolder> {

    private Context context;
    private ArrayList<Movie> arrayList;

    public FavMovieAdapter(Context context, ArrayList<Movie> itemArrayList) {
        this.context = context;
        this.arrayList = itemArrayList;
    }

    @NonNull
    @Override
    public FavMovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);
        return new FavMovieHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull FavMovieHolder rvHolder, @SuppressLint("RecyclerView") final int i) {

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.background);

        rvHolder.mTxtTitle.setText(arrayList.get(i).getTitle());
        rvHolder.mTxtDate.setText(arrayList.get(i).getDate());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + arrayList.get(i).getPosterImg())
                .into(rvHolder.mImage);

        rvHolder.mCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", arrayList.get(i).getTitle());
            intent.putExtra("date", arrayList.get(i).getDate());
            intent.putExtra("backdrop", "https://image.tmdb.org/t/p/w500" + arrayList.get(i).getBackdropImg());
            intent.putExtra("detail", arrayList.get(i).getOverview());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class FavMovieHolder extends RecyclerView.ViewHolder {

        TextView mTxtTitle, mTxtDate;
        CardView mCard;
        ImageView mImage;

        FavMovieHolder(@NonNull View itemView) {
            super(itemView);

            mTxtTitle = itemView.findViewById(R.id.txtTitle);
            mTxtDate = itemView.findViewById(R.id.txtDate);
            mCard = itemView.findViewById(R.id.cardView);
            mImage = itemView.findViewById(R.id.imgPoster);
        }
    }
}
