package com.example.itunes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itunes.MusicDetail;
import com.example.itunes.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.itunes.Model.MusicResult;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class MusicRecyclerViewAdapter extends RecyclerView.Adapter<MusicRecyclerViewAdapter.RowHolder> {

    private ArrayList<MusicResult> musicResultList;
    Context contex;
    Activity activity;

    public MusicRecyclerViewAdapter(ArrayList<MusicResult> musicResultList, Context contex, Activity activity) {
        this.musicResultList = musicResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_music,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(musicResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return musicResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutMusic;
        ImageView imgMusicArtworkUrl100, imgMusicPlay, imgMusicPause;
        TextView textMusicArtistName, textMusicTrackName;
        MediaPlayer mediaPlayer = new MediaPlayer();

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final MusicResult musicResultModel, Integer position){

            linearLayoutMusic = (LinearLayout) itemView.findViewById(R.id.linearLayout_music);

            imgMusicArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_musicArtworkUrl100);
            imgMusicPlay = (ImageView) itemView.findViewById(R.id.image_musicPlay);
            imgMusicPause = (ImageView) itemView.findViewById(R.id.image_musicPause);

            textMusicArtistName = (TextView) itemView.findViewById(R.id.text_musicArtistName);
            textMusicTrackName = (TextView) itemView.findViewById(R.id.text_musicTrackName);

            Picasso.get()
                    .load(musicResultModel.getArtworkUrl100())
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imgMusicArtworkUrl100);

            if(musicResultModel.getArtistName() != null)
                textMusicArtistName.setText(musicResultModel.getArtistName());
            if(musicResultModel.getTrackName() != null)
                textMusicTrackName.setText(musicResultModel.getTrackName());

            /*
            if(musicResultModel.getTrackTimeMillis() != null)
            {
                int time = Integer.valueOf(musicResultModel.getTrackTimeMillis());
                int topSaniye = time / 1000;
                int dakika = topSaniye/ 60;
                int saniye = topSaniye % 60;
                if(saniye < 10)
                    textMusicTrackTimeMillis.setText(dakika + ":0" + saniye);
                else
                    textMusicTrackTimeMillis.setText(dakika + ":" + saniye);
            }

            if(musicResultModel.getTrackPrice() != null)
                textMusicTrackPrice.setText(musicResultModel.getTrackPrice());
            */

            imgMusicPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgMusicPlay.setVisibility(View.GONE);
                    imgMusicPause.setVisibility(View.VISIBLE);

                    try {
                        mediaPlayer.stop();
                        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().
                                setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build());
                        mediaPlayer.setDataSource(contex, Uri.parse(musicResultList.get(position).getPreviewUrl()));
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                }
            });

            imgMusicPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgMusicPlay.setVisibility(View.VISIBLE);
                    imgMusicPause.setVisibility(View.GONE);

                    mediaPlayer.pause();
                }
            });

            linearLayoutMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(contex, MusicDetail.class);
                    intent.putExtra("Status","Müzik");
                    intent.putExtra("ArtworkUrl100",musicResultList.get(position).getArtworkUrl100());
                    intent.putExtra("PreviewUrl",musicResultList.get(position).getPreviewUrl());
                    intent.putExtra("ArtistName",musicResultList.get(position).getArtistName());
                    intent.putExtra("TrackName",musicResultList.get(position).getTrackName());
                    intent.putExtra("TrackTimeMillis",musicResultList.get(position).getTrackTimeMillis());
                    intent.putExtra("TrackPrice",musicResultList.get(position).getTrackPrice());
                    intent.putExtra("ReleaseDate",musicResultList.get(position).getReleaseDate());
                    intent.putExtra("PrimaryGenreName",musicResultList.get(position).getPrimaryGenreName());
                    activity.startActivity(intent);

                }
            });

        }
    }

}
