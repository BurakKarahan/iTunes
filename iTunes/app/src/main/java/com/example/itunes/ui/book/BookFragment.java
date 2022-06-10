package com.example.itunes.ui.book;

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

import com.example.itunes.Adapter.BookRecyclerViewAdapter;
import com.example.itunes.Adapter.MovieRecyclerViewAdapter;
import com.example.itunes.Adapter.MusicRecyclerViewAdapter;
import com.example.itunes.Adapter.MusicVideoRecyclerViewAdapter;
import com.example.itunes.Adapter.ShortFilmRecyclerViewAdapter;
import com.example.itunes.Adapter.TvShowRecyclerViewAdapter;
import com.example.itunes.Model.BookResult;
import com.example.itunes.Model.BookRoot;
import com.example.itunes.Model.MovieRoot;
import com.example.itunes.Model.MusicResult;
import com.example.itunes.RestApi.ManagerAll;
import com.example.itunes.databinding.FragmentBookBinding;
import com.example.itunes.ui.music.MusicViewModel;

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends Fragment {

    private FragmentBookBinding binding;

    ArrayList<BookResult> bookResultModels;
    RecyclerView bookRecyclerView;
    BookRecyclerViewAdapter bookRecyclerViewAdapter;

    ShimmerLayout placeholderBook;

    EditText editTextBookSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookViewModel bookViewModel =
                new ViewModelProvider(this).get(BookViewModel.class);

        binding = FragmentBookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bookRecyclerView = binding.recyclerViewBook;
        editTextBookSearch = binding.editTextSearch;
        placeholderBook = binding.placeholderBook;

        final LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getContext());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);

        Call<BookRoot> BookData = ManagerAll.Web().ManagerBook("","book","ebook");
        PlaceHolderStatus(bookRecyclerView, placeholderBook, true);
        BookData.enqueue(new Callback<BookRoot>() {
            @Override
            public void onResponse(Call<BookRoot> call, Response<BookRoot> response) {
                if (response.isSuccessful()) {
                    bookResultModels = new ArrayList<>(response.body().getResults());
                    bookRecyclerView.setLayoutManager(linearLayoutManagerVertical);
                    bookRecyclerViewAdapter = new BookRecyclerViewAdapter(bookResultModels, getContext(), getActivity());
                    bookRecyclerView.setAdapter(bookRecyclerViewAdapter);
                }
                PlaceHolderStatus(bookRecyclerView, placeholderBook, false);
            }

            @Override
            public void onFailure(Call<BookRoot> call, Throwable t) {

            }
        });

        editTextBookSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    Call<BookRoot> BookData = ManagerAll.Web().ManagerBook("",editTextBookSearch.getText().toString(),"ebook");
                    PlaceHolderStatus(bookRecyclerView, placeholderBook, true);
                    BookData.enqueue(new Callback<BookRoot>() {
                        @Override
                        public void onResponse(Call<BookRoot> call, Response<BookRoot> response) {
                            if (response.isSuccessful()) {
                                bookResultModels = new ArrayList<>(response.body().getResults());
                                bookRecyclerViewAdapter = new BookRecyclerViewAdapter(bookResultModels, getContext(), getActivity());
                                bookRecyclerView.setAdapter(bookRecyclerViewAdapter);
                            }
                            PlaceHolderStatus(bookRecyclerView, placeholderBook, false);
                        }

                        @Override
                        public void onFailure(Call<BookRoot> call, Throwable t) {

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
