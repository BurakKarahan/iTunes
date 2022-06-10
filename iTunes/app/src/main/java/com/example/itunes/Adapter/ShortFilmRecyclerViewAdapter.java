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
import com.example.itunes.MovieDetail;
import com.example.itunes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShortFilmRecyclerViewAdapter extends RecyclerView.Adapter<ShortFilmRecyclerViewAdapter.RowHolder> {

    private ArrayList<MovieResult> shortFilmResultList;
    Context contex;
    Activity activity;

    public ShortFilmRecyclerViewAdapter(ArrayList<MovieResult> shortFilmResultList, Context contex, Activity activity) {
        this.shortFilmResultList = shortFilmResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ShortFilmRecyclerViewAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_movie,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortFilmRecyclerViewAdapter.RowHolder holder, int position) {
        holder.bind(shortFilmResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return shortFilmResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutShortFilm;
        ImageView imgShortFilmArtworkUrl100;
        TextView textShortFilmArtistName, textShortFilmTrackName;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final MovieResult movieResultModel, Integer position) {

            Log.i("Adapter", movieResultModel.getArtistName().toString());

            linearLayoutShortFilm = (LinearLayout) itemView.findViewById(R.id.linearLayout_movie);

            imgShortFilmArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_movieArtworkUrl100);

            textShortFilmArtistName = (TextView) itemView.findViewById(R.id.text_movieArtistName);
            textShortFilmTrackName = (TextView) itemView.findViewById(R.id.text_movieTrackName);

            Picasso.get()
                    .load(movieResultModel.getArtworkUrl100())
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imgShortFilmArtworkUrl100);

            if(movieResultModel.getArtistName() != null)
                textShortFilmArtistName.setText(movieResultModel.getArtistName());
            if(movieResultModel.getTrackName() != null)
                textShortFilmTrackName.setText(movieResultModel.getTrackName());

            linearLayoutShortFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contex, MovieDetail.class);
                    intent.putExtra("ArtworkUrl100",shortFilmResultList.get(position).getArtworkUrl100());
                    intent.putExtra("PreviewUrl",shortFilmResultList.get(position).getPreviewUrl());
                    intent.putExtra("ArtistName",shortFilmResultList.get(position).getArtistName());
                    intent.putExtra("TrackName",shortFilmResultList.get(position).getTrackName());
                    intent.putExtra("TrackTimeMillis",shortFilmResultList.get(position).getTrackTimeMillis());
                    intent.putExtra("TrackPrice",shortFilmResultList.get(position).getTrackPrice());
                    intent.putExtra("LongDescription",shortFilmResultList.get(position).getLongDescription());
                    intent.putExtra("ReleaseDate",shortFilmResultList.get(position).getReleaseDate());
                    activity.startActivity(intent);
                }
            });

        }

    }
}
