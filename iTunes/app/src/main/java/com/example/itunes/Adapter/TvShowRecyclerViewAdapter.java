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

public class TvShowRecyclerViewAdapter extends  RecyclerView.Adapter<TvShowRecyclerViewAdapter.RowHolder>{

    private ArrayList<MovieResult> tvShowResultList;
    Context contex;
    Activity activity;

    public TvShowRecyclerViewAdapter(ArrayList<MovieResult> tvShowResultList, Context contex, Activity activity) {
        this.tvShowResultList = tvShowResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_movie,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowRecyclerViewAdapter.RowHolder holder, int position) {
        holder.bind(tvShowResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return tvShowResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutTvShow;
        ImageView imgTvShowArtworkUrl100;
        TextView textTvShowArtistName, textTvShowTrackName;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final MovieResult movieResultModel, Integer position)
        {
            linearLayoutTvShow = (LinearLayout) itemView.findViewById(R.id.linearLayout_movie);

            imgTvShowArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_movieArtworkUrl100);

            textTvShowArtistName = (TextView) itemView.findViewById(R.id.text_movieArtistName);
            textTvShowTrackName = (TextView) itemView.findViewById(R.id.text_movieTrackName);

            Log.i("ıoıoıoıoıoıoı", movieResultModel.getArtworkUrl100().toString());

            Picasso.get()
                    .load(movieResultModel.getArtworkUrl100())
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imgTvShowArtworkUrl100);

            if(movieResultModel.getArtistName() != null)
                textTvShowArtistName.setText(movieResultModel.getArtistName());
            if(movieResultModel.getTrackName() != null)
                textTvShowTrackName.setText(movieResultModel.getTrackName());

            linearLayoutTvShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contex, MovieDetail.class);
                    intent.putExtra("ArtworkUrl100",tvShowResultList.get(position).getArtworkUrl100());
                    intent.putExtra("PreviewUrl",tvShowResultList.get(position).getPreviewUrl());
                    intent.putExtra("ArtistName",tvShowResultList.get(position).getArtistName());
                    intent.putExtra("TrackName",tvShowResultList.get(position).getTrackName());
                    intent.putExtra("TrackTimeMillis",tvShowResultList.get(position).getTrackTimeMillis());
                    intent.putExtra("TrackPrice",tvShowResultList.get(position).getTrackPrice());
                    intent.putExtra("LongDescription",tvShowResultList.get(position).getLongDescription());
                    intent.putExtra("ReleaseDate",tvShowResultList.get(position).getReleaseDate());
                    activity.startActivity(intent);
                }
            });

        }
    }
}
