package com.example.itunes.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itunes.Model.MusicResult;
import com.example.itunes.MusicDetail;
import com.example.itunes.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class MusicVideoRecyclerViewAdapter extends RecyclerView.Adapter<MusicVideoRecyclerViewAdapter.RowHolder> {

    private ArrayList<MusicResult> musicResultList;
    Context contex;
    Activity activity;


    public MusicVideoRecyclerViewAdapter(ArrayList<MusicResult> musicResultList, Context contex, Activity activity) {
        this.musicResultList = musicResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_music_video,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicVideoRecyclerViewAdapter.RowHolder holder, int position) {
        holder.bind(musicResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return musicResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutMusicVideo;
        ImageView imgMusicVideoArtworkUrl100;
        TextView textMusicVideoArtistName, textMusicVideoTrackName, textMusicVideoTrackTimeMillis;
        ImageView imgMusicVideoPlay, imgMusicVideoPause;

        MediaPlayer mediaPlayer = new MediaPlayer();

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final MusicResult musicVideoResultModel, Integer position){

            linearLayoutMusicVideo = (LinearLayout) itemView.findViewById(R.id.linearLayout_musicVideo);

            imgMusicVideoArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_musicVideo_artworkUrl100);
            imgMusicVideoPlay = (ImageView) itemView.findViewById(R.id.image_musicVideoPlay);
            imgMusicVideoPause = (ImageView) itemView.findViewById(R.id.image_musicVideoPause);

            textMusicVideoArtistName = (TextView) itemView.findViewById(R.id.text_musicVideo_artistName);
            textMusicVideoTrackName = (TextView) itemView.findViewById(R.id.text_musicVideo_trackName);
            textMusicVideoTrackTimeMillis = (TextView)  itemView.findViewById(R.id.text_musicVideo_trackTimeMillis);

            Picasso.get()
                    .load(musicVideoResultModel.getArtworkUrl100())
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imgMusicVideoArtworkUrl100);

            if(musicVideoResultModel.getArtistName() != null)
                textMusicVideoArtistName.setText(musicVideoResultModel.getArtistName());
            if(musicVideoResultModel.getTrackName() != null)
                textMusicVideoTrackName.setText(musicVideoResultModel.getTrackName());
            if(musicVideoResultModel.getTrackTimeMillis() != null)
            {
                int time = Integer.valueOf(musicVideoResultModel.getTrackTimeMillis());
                int topSaniye = time / 1000;
                int dakika = topSaniye/ 60;
                int saniye = topSaniye % 60;
                if(saniye < 10)
                    textMusicVideoTrackTimeMillis.setText(dakika + ":0" + saniye + " dk");
                else
                    textMusicVideoTrackTimeMillis.setText(dakika + ":" + saniye + " dk");
            }
            /*
            if(musicVideoResultModel.getTrackPrice() != null)
                textMusicVideoTrackPrice.setText(musicVideoResultModel.getTrackPrice());
            */

            imgMusicVideoPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgMusicVideoPlay.setVisibility(View.GONE);
                    imgMusicVideoPause.setVisibility(View.VISIBLE);

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

            imgMusicVideoPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgMusicVideoPlay.setVisibility(View.VISIBLE);
                    imgMusicVideoPause.setVisibility(View.GONE);

                    mediaPlayer.pause();
                }
            });

            linearLayoutMusicVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(contex, MusicDetail.class);
                    intent.putExtra("Status","Video");
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
