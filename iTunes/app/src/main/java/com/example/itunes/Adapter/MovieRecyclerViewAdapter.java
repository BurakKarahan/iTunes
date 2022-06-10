package com.example.itunes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itunes.Model.MovieResult;
import com.example.itunes.Model.MusicResult;
import com.example.itunes.MovieDetail;
import com.example.itunes.MusicDetail;
import com.example.itunes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.RowHolder>{

    private ArrayList<MovieResult> movieResultList;
    Context contex;
    Activity activity;

    public MovieRecyclerViewAdapter(ArrayList<MovieResult> movieResultList, Context contex, Activity activity) {
        this.movieResultList = movieResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_movie,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(movieResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutMovie;
        ImageView imgMovieArtworkUrl100;
        TextView textMovieArtistName, textMovieTrackName;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final MovieResult movieResultModel, Integer position)
        {
            linearLayoutMovie = (LinearLayout) itemView.findViewById(R.id.linearLayout_movie);

            imgMovieArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_movieArtworkUrl100);

            textMovieArtistName = (TextView) itemView.findViewById(R.id.text_movieArtistName);
            textMovieTrackName = (TextView) itemView.findViewById(R.id.text_movieTrackName);

            Log.i("tytytyty", movieResultModel.getArtworkUrl100().toString());

            Picasso.get()
                    .load(movieResultModel.getArtworkUrl100())
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imgMovieArtworkUrl100);

            if(movieResultModel.getArtistName() != null)
                textMovieArtistName.setText(movieResultModel.getArtistName());
            if(movieResultModel.getTrackName() != null)
                textMovieTrackName.setText(movieResultModel.getTrackName());

            linearLayoutMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contex, MovieDetail.class);
                    intent.putExtra("ArtworkUrl100",movieResultList.get(position).getArtworkUrl100());
                    intent.putExtra("PreviewUrl",movieResultList.get(position).getPreviewUrl());
                    intent.putExtra("ArtistName",movieResultList.get(position).getArtistName());
                    intent.putExtra("TrackName",movieResultList.get(position).getTrackName());
                    intent.putExtra("TrackTimeMillis",movieResultList.get(position).getTrackTimeMillis());
                    intent.putExtra("TrackPrice",movieResultList.get(position).getTrackPrice());
                    intent.putExtra("LongDescription",movieResultList.get(position).getLongDescription());
                    intent.putExtra("ReleaseDate",movieResultList.get(position).getReleaseDate());
                    activity.startActivity(intent);
                }
            });

        }
    }
}
