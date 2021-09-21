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
import com.example.tugas12_anisahasna.activity.SubmitFavMovieActivity;
import com.example.tugas12_anisahasna.response.ResultsItem;

import java.util.ArrayList;

public class AddFavMovAdapter extends RecyclerView.Adapter<AddFavMovAdapter.AddFavMovAdapterHolder> {

    private Context context;
    private ArrayList<ResultsItem> arrayList;

    public AddFavMovAdapter(Context context, ArrayList<ResultsItem> itemArrayList) {
        this.context = context;
        this.arrayList = itemArrayList;
    }

    @NonNull
    @Override
    public AddFavMovAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_movie, null);
        return new AddFavMovAdapterHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull AddFavMovAdapterHolder rvHolder, @SuppressLint("RecyclerView") final int i) {

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.background);

        rvHolder.mTxtTitle.setText(arrayList.get(i).getTitle());
        rvHolder.mTxtDate.setText(arrayList.get(i).getReleaseDate());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + arrayList.get(i).getPosterPath())
                .into(rvHolder.mImage);

        rvHolder.mCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, SubmitFavMovieActivity.class);
            intent.putExtra("title", arrayList.get(i).getTitle());
            intent.putExtra("date", arrayList.get(i).getReleaseDate());
            intent.putExtra("poster", "https://image.tmdb.org/t/p/w500" + arrayList.get(i).getPosterPath());
            intent.putExtra("backdrop", "https://image.tmdb.org/t/p/w500" + arrayList.get(i).getBackdropPath());
            intent.putExtra("detail", arrayList.get(i).getOverview());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AddFavMovAdapterHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTxtDate, mTxtTitle;
        CardView mCard;

        AddFavMovAdapterHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.imgPoster);
            mTxtDate = itemView.findViewById(R.id.txtDate);
            mTxtTitle = itemView.findViewById(R.id.txtTitle);
            mCard = itemView.findViewById(R.id.cardView);
        }
    }
}
