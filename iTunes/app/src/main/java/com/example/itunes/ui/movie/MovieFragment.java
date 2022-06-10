package com.example.itunes.ui.movie;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itunes.Adapter.MovieRecyclerViewAdapter;
import com.example.itunes.Adapter.MusicRecyclerViewAdapter;
import com.example.itunes.Adapter.MusicVideoRecyclerViewAdapter;
import com.example.itunes.Adapter.ShortFilmRecyclerViewAdapter;
import com.example.itunes.Adapter.TvShowRecyclerViewAdapter;
import com.example.itunes.Model.MovieResult;
import com.example.itunes.Model.MovieRoot;
import com.example.itunes.Model.MusicRoot;
import com.example.itunes.RestApi.ManagerAll;
import com.example.itunes.databinding.FragmentMovieBinding;

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    ArrayList<MovieResult> movieResultModels;
    RecyclerView movieRecyclerView;
    MovieRecyclerViewAdapter movieRecyclerViewAdapter;

    ArrayList<MovieResult> shortFilmResultModels;
    RecyclerView shortFilmRecyclerView;
    ShortFilmRecyclerViewAdapter shortFilmRecyclerViewAdapter;

    ArrayList<MovieResult> tvShowResultModels;
    RecyclerView tvShowRecyclerView;
    TvShowRecyclerViewAdapter tvShowRecyclerViewAdapter;

    ShimmerLayout placeholderMovie, placeholderShortFilm, placeholderTvShow;
    EditText editTextMovieSearch;
    TextView textTitleMovie, textTitleShortFilm, textTitleTvShow;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MovieViewModel movieViewModel =
                new ViewModelProvider(this).get(MovieViewModel.class);

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        final TextView textView = binding.textMovie;
        movieViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */

        movieRecyclerView = binding.recyclerViewMovie;
        shortFilmRecyclerView = binding.recyclerViewShortFilm;
        tvShowRecyclerView = binding.recyclerViewTvShow;

        editTextMovieSearch = binding.editTextSearch;
        textTitleMovie = binding.textTitleMovie;
        textTitleShortFilm = binding.textTitleShortFilm;
        textTitleTvShow = binding.textTitleTvShow;

        placeholderMovie = binding.placeholderMovie;
        placeholderShortFilm = binding.placeholderShortFilm;
        placeholderTvShow = binding.placeholderTvShow;

        final LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(getContext());
        linearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);

        Call<MovieRoot> MovieData = ManagerAll.Web().ManagerMovie("","movie","movie");
        PlaceHolderStatus(movieRecyclerView, placeholderMovie, true);
        MovieData.enqueue(new Callback<MovieRoot>() {
            @Override
            public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                if (response.isSuccessful()) {
                    movieResultModels = new ArrayList<>(response.body().getResults());
                    movieRecyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                    movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movieResultModels, getContext(), getActivity());
                    movieRecyclerView.setAdapter(movieRecyclerViewAdapter);

                    if(response.body().getResultCount() > 0)
                        textTitleMovie.setVisibility(View.VISIBLE);
                    else
                        textTitleMovie.setVisibility(View.GONE);
                }
                PlaceHolderStatus(movieRecyclerView, placeholderMovie, false);
            }

            @Override
            public void onFailure(Call<MovieRoot> call, Throwable t) {

            }
        });

        final LinearLayoutManager linearLayoutManagerHorizontal2 = new LinearLayoutManager(getContext());
        linearLayoutManagerHorizontal2.setOrientation(LinearLayoutManager.HORIZONTAL);

        Call<MovieRoot> ShortFilmData = ManagerAll.Web().ManagerMovie("","movie","shortFilm");
        PlaceHolderStatus(shortFilmRecyclerView, placeholderShortFilm, true);
        ShortFilmData.enqueue(new Callback<MovieRoot>() {
            @Override
            public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                if (response.isSuccessful()) {
                    shortFilmResultModels = new ArrayList<>(response.body().getResults());
                    shortFilmRecyclerView.setLayoutManager(linearLayoutManagerHorizontal2);
                    shortFilmRecyclerViewAdapter = new ShortFilmRecyclerViewAdapter(shortFilmResultModels, getContext(), getActivity());
                    shortFilmRecyclerView.setAdapter(shortFilmRecyclerViewAdapter);

                    if(response.body().getResultCount() > 0)
                        textTitleShortFilm.setVisibility(View.VISIBLE);
                    else
                        textTitleShortFilm.setVisibility(View.GONE);
                }
                PlaceHolderStatus(shortFilmRecyclerView, placeholderShortFilm, false);
            }

            @Override
            public void onFailure(Call<MovieRoot> call, Throwable t) {

            }
        });

        final LinearLayoutManager linearLayoutManagerHorizontal3 = new LinearLayoutManager(getContext());
        linearLayoutManagerHorizontal3.setOrientation(LinearLayoutManager.HORIZONTAL);

        Call<MovieRoot> TvShowData = ManagerAll.Web().ManagerMovie("","movie","tvShow");
        PlaceHolderStatus(tvShowRecyclerView, placeholderTvShow, true);
        TvShowData.enqueue(new Callback<MovieRoot>() {
            @Override
            public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                if (response.isSuccessful()) {
                    tvShowResultModels = new ArrayList<>(response.body().getResults());
                    tvShowRecyclerView.setLayoutManager(linearLayoutManagerHorizontal3);
                    tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(tvShowResultModels, getContext(), getActivity());
                    tvShowRecyclerView.setAdapter(tvShowRecyclerViewAdapter);

                    if(response.body().getResultCount() > 0)
                        textTitleTvShow.setVisibility(View.VISIBLE);
                     else
                        textTitleTvShow.setVisibility(View.GONE);
                }
                PlaceHolderStatus(tvShowRecyclerView, placeholderTvShow, false);
            }

            @Override
            public void onFailure(Call<MovieRoot> call, Throwable t) {

            }
        });

        editTextMovieSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    Call<MovieRoot> MovieData = ManagerAll.Web().ManagerMovie("",editTextMovieSearch.getText().toString(),"movie");
                    PlaceHolderStatus(movieRecyclerView, placeholderMovie, true);
                    MovieData.enqueue(new Callback<MovieRoot>() {
                        @Override
                        public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                            if (response.isSuccessful()) {
                                movieResultModels = new ArrayList<>(response.body().getResults());
                                movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movieResultModels, getContext(), getActivity());
                                movieRecyclerView.setAdapter(movieRecyclerViewAdapter);

                                if(response.body().getResultCount() > 0)
                                    textTitleMovie.setVisibility(View.VISIBLE);
                                else
                                    textTitleMovie.setVisibility(View.GONE);
                            }
                            PlaceHolderStatus(movieRecyclerView, placeholderMovie, false);
                        }

                        @Override
                        public void onFailure(Call<MovieRoot> call, Throwable t) {

                        }
                    });

                    Call<MovieRoot> ShortFilmData = ManagerAll.Web().ManagerMovie("",editTextMovieSearch.getText().toString(),"shortFilm");
                    PlaceHolderStatus(shortFilmRecyclerView, placeholderShortFilm, true);
                    ShortFilmData.enqueue(new Callback<MovieRoot>() {
                        @Override
                        public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                            if (response.isSuccessful()) {
                                shortFilmResultModels = new ArrayList<>(response.body().getResults());
                                shortFilmRecyclerViewAdapter = new ShortFilmRecyclerViewAdapter(shortFilmResultModels, getContext(), getActivity());
                                shortFilmRecyclerView.setAdapter(shortFilmRecyclerViewAdapter);

                                if(response.body().getResultCount() > 0)
                                    textTitleShortFilm.setVisibility(View.VISIBLE);
                                else
                                    textTitleShortFilm.setVisibility(View.GONE);
                            }
                            PlaceHolderStatus(shortFilmRecyclerView, placeholderShortFilm, false);
                        }

                        @Override
                        public void onFailure(Call<MovieRoot> call, Throwable t) {

                        }
                    });

                    Call<MovieRoot> TvShowData = ManagerAll.Web().ManagerMovie("",editTextMovieSearch.getText().toString(),"tvShow");
                    PlaceHolderStatus(tvShowRecyclerView, placeholderTvShow, true);
                    TvShowData.enqueue(new Callback<MovieRoot>() {
                        @Override
                        public void onResponse(Call<MovieRoot> call, Response<MovieRoot> response) {

                            if (response.isSuccessful()) {
                                tvShowResultModels = new ArrayList<>(response.body().getResults());
                                tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(tvShowResultModels, getContext(), getActivity());
                                tvShowRecyclerView.setAdapter(tvShowRecyclerViewAdapter);

                                if(response.body().getResultCount() > 0)
                                    textTitleTvShow.setVisibility(View.VISIBLE);
                                else
                                    textTitleTvShow.setVisibility(View.GONE);
                            }
                            PlaceHolderStatus(tvShowRecyclerView, placeholderTvShow, false);
                        }

                        @Override
                        public void onFailure(Call<MovieRoot> call, Throwable t) {

                        }
                    });

                    return true;
                }
                return false;
            }
        });

        return root;
    }

    public void PlaceHolderStatus(RecyclerView recyclerView, ShimmerLayout shimmerLayout, boolean Status)
    {
        if(Status == true)
        {
            recyclerView.setVisibility(View.GONE);
            shimmerLayout.setVisibility(View.VISIBLE);
            shimmerLayout.startShimmerAnimation();
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            shimmerLayout.setVisibility(View.GONE);
            shimmerLayout.stopShimmerAnimation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}