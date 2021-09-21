package com.example.tugas12_anisahasna.fragment;

import static com.example.tugas12_anisahasna.App.db;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tugas12_anisahasna.activity.AddFavMovieActivity;
import com.example.tugas12_anisahasna.activity.SubmitFavMovieActivity;
import com.example.tugas12_anisahasna.adapter.FavMovieAdapter;
import com.example.tugas12_anisahasna.R;
import com.example.tugas12_anisahasna.database.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton addFavBtn;
    RecyclerView recyclerView;
    FavMovieAdapter favMovieAdapter;
    Button deleteBtn;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        addFavBtn = view.findViewById(R.id.favAdd);

        addFavBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddFavMovieActivity.class);
            startActivity(intent);
        });

        recyclerView = view.findViewById(R.id.rcvFavMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<Movie> resultsItems = (ArrayList<Movie>) db.movieDao().getAllMovie();
        favMovieAdapter = new FavMovieAdapter(getActivity(), resultsItems);
        recyclerView.setAdapter(favMovieAdapter);

        deleteBtn = view.findViewById(R.id.deleteBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.movieDao().deleteAllMovie();
                Toast.makeText(getContext(), "Berhasil menghapus semua film favorit", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}