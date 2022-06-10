package com.example.itunes.ui.music;

import android.os.Bundle;
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

import com.example.itunes.Adapter.MusicRecyclerViewAdapter;
import com.example.itunes.Adapter.MusicVideoRecyclerViewAdapter;
import com.example.itunes.Model.MusicResult;
import com.example.itunes.Model.MusicRoot;
import com.example.itunes.RestApi.ManagerAll;
import com.example.itunes.databinding.FragmentMusicBinding;

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicFragment extends Fragment {

    private FragmentMusicBinding binding;

    ArrayList<MusicResult> musicResultModels;
    RecyclerView musicRecyclerView;
    MusicRecyclerViewAdapter musicRecyclerViewAdapter;

    ArrayList<MusicResult> musicVideoResultModels;
    RecyclerView musicVideoRecyclerView;
    MusicVideoRecyclerViewAdapter musicVideoRecyclerViewAdapter;

    ShimmerLayout placeholderMusicHorizontal, placeholderMusicVertical;

    EditText editTextMusicSearch;

    TextView textTitleMusic, textTitleMusicVideo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MusicViewModel musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        placeholderMusicHorizontal = binding.placeholderMusicHorizontal;
        placeholderMusicVertical = binding.placeholderMusicVertical;

        musicRecyclerView = binding.recyclerViewMusic;
        musicVideoRecyclerView = binding.recyclerViewMusicVideo;

        editTextMusicSearch = binding.editTextSearch;

        textTitleMusic = binding.textTitleMusic;
        textTitleMusicVideo = binding.textTitleMusicVideo;

        final LinearLayoutManager linearLayoutManagerHorizontal = new LinearLayoutManager(getContext());
        linearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);

        Call<MusicRoot> MusicData = ManagerAll.Web().ManagerMusic("tr","song","music");
        PlaceHolderStatus(musicRecyclerView, placeholderMusicHorizontal, true);
        MusicData.enqueue(new Callback<MusicRoot>() {
            @Override
            public void onResponse(Call<MusicRoot> call, Response<MusicRoot> response) {

                if (response.isSuccessful()) {
                    musicResultModels = new ArrayList<>(response.body().getResults());
                    musicRecyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                    musicRecyclerViewAdapter = new MusicRecyclerViewAdapter(musicResultModels, getContext(), getActivity());
                    musicRecyclerView.setAdapter(musicRecyclerViewAdapter);

                    if(response.body().getResultCount() > 0)
                        textTitleMusic.setVisibility(View.VISIBLE);
                    else
                        textTitleMusic.setVisibility(View.GONE);
                }
                PlaceHolderStatus(musicRecyclerView, placeholderMusicHorizontal, false);
            }

            @Override
            public void onFailure(Call<MusicRoot> call, Throwable t) {

            }
        });

        final LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getContext());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);

        Call<MusicRoot> MusicVideoData = ManagerAll.Web().ManagerMusic("tr","song","musicVideo");
        PlaceHolderStatus(musicVideoRecyclerView, placeholderMusicVertical, true);
        MusicVideoData.enqueue(new Callback<MusicRoot>() {
            @Override
            public void onResponse(Call<MusicRoot> call, Response<MusicRoot> response) {

                if (response.isSuccessful()) {
                    musicVideoResultModels = new ArrayList<>(response.body().getResults());
                    musicVideoRecyclerView.setLayoutManager(linearLayoutManagerVertical);
                    musicVideoRecyclerViewAdapter = new MusicVideoRecyclerViewAdapter(musicVideoResultModels, getContext(), getActivity());
                    musicVideoRecyclerView.setAdapter(musicVideoRecyclerViewAdapter);

                    if(response.body().getResultCount() > 0)
                        textTitleMusicVideo.setVisibility(View.VISIBLE);
                    else
                        textTitleMusicVideo.setVisibility(View.GONE);
                }
                PlaceHolderStatus(musicVideoRecyclerView, placeholderMusicVertical, false);
            }

            @Override
            public void onFailure(Call<MusicRoot> call, Throwable t) {

            }
        });

        editTextMusicSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    Call<MusicRoot> MusicData = ManagerAll.Web().ManagerMusic("tr",editTextMusicSearch.getText().toString(),"music");
                    PlaceHolderStatus(musicRecyclerView, placeholderMusicHorizontal, true);
                    MusicData.enqueue(new Callback<MusicRoot>() {
                        @Override
                        public void onResponse(Call<MusicRoot> call, Response<MusicRoot> response) {

                            if (response.isSuccessful()) {
                                musicResultModels = new ArrayList<>(response.body().getResults());
                                musicRecyclerViewAdapter = new MusicRecyclerViewAdapter(musicResultModels, getContext(), getActivity());
                                musicRecyclerView.setAdapter(musicRecyclerViewAdapter);

                                if(response.body().getResultCount() > 0)
                                    textTitleMusic.setVisibility(View.VISIBLE);
                                else
                                    textTitleMusic.setVisibility(View.GONE);
                            }
                            PlaceHolderStatus(musicRecyclerView, placeholderMusicHorizontal, false);
                        }

                        @Override
                        public void onFailure(Call<MusicRoot> call, Throwable t) {

                        }
                    });

                    Call<MusicRoot> MusicVideoData = ManagerAll.Web().ManagerMusic("tr",editTextMusicSearch.getText().toString(),"musicVideo");
                    PlaceHolderStatus(musicVideoRecyclerView, placeholderMusicVertical, true);
                    MusicVideoData.enqueue(new Callback<MusicRoot>() {
                        @Override
                        public void onResponse(Call<MusicRoot> call, Response<MusicRoot> response) {

                            if (response.isSuccessful()) {
                                musicVideoResultModels = new ArrayList<>(response.body().getResults());
                                musicVideoRecyclerViewAdapter = new MusicVideoRecyclerViewAdapter(musicVideoResultModels, getContext(), getActivity());
                                musicVideoRecyclerView.setAdapter(musicVideoRecyclerViewAdapter);

                                if(response.body().getResultCount() > 0)
                                    textTitleMusicVideo.setVisibility(View.VISIBLE);
                                else
                                    textTitleMusicVideo.setVisibility(View.GONE);
                            }
                            PlaceHolderStatus(musicVideoRecyclerView, placeholderMusicVertical, false);
                        }

                        @Override
                        public void onFailure(Call<MusicRoot> call, Throwable t) {

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