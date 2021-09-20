package com.example.tugas12_anisahasna.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.activity.FavDetailActivity;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_movie_card, null);
        return new FavMovieHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull FavMovieHolder rvHolder, @SuppressLint("RecyclerView") final int i) {

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.background);

        rvHolder.mTxtTitle.setText(arrayList.get(i).getTitle());
        rvHolder.mTxtGenre.setText(arrayList.get(i).getGenre());
        rvHolder.mTxtDate.setText(arrayList.get(i).getDateFav());

        rvHolder.mCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, FavDetailActivity.class);
            intent.putExtra("title", arrayList.get(i).getTitle());
            intent.putExtra("genre", arrayList.get(i).getGenre());
            intent.putExtra("detail", arrayList.get(i).getOverview());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class FavMovieHolder extends RecyclerView.ViewHolder {

        TextView mTxtGenre, mTxtTitle, mTxtDate;
        CardView mCard;

        FavMovieHolder(@NonNull View itemView) {
            super(itemView);

            mTxtGenre = itemView.findViewById(R.id.genreTxt);
            mTxtTitle = itemView.findViewById(R.id.titleTxt);
            mTxtDate = itemView.findViewById(R.id.dateTxt);
            mCard = itemView.findViewById(R.id.cardView);
        }
    }
}
