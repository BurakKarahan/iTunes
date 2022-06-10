package com.example.itunes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MusicDetail extends AppCompatActivity {

    ImageView imageArtworkUrl100, imagePlay, imagePause;
    TextView textArtistName, textTrackName, textTrackTimeMillis, textTrackPrice, textReleaseDate, textPrimaryGenreName;

    MediaPlayer mediaPlayer = new MediaPlayer();
    Uri UriMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);

        imageArtworkUrl100 = (ImageView) findViewById(R.id.image_musicDetailArtworkUrl100);
        imagePlay = (ImageView) findViewById(R.id.image_musicDetailPlay);
        imagePause = (ImageView) findViewById(R.id.image_musicDetailPause);

        textArtistName = (TextView) findViewById(R.id.text_musicDetailArtistName);
        textTrackName = (TextView) findViewById(R.id.text_musicDetailTrackName);
        textTrackTimeMillis = (TextView) findViewById(R.id.text_musicDetailTrackTimeMillis);
        textTrackPrice = (TextView) findViewById(R.id.text_musicDetailTrackPrice);
        textReleaseDate = (TextView) findViewById(R.id.text_musicDetailReleaseDate);
        textPrimaryGenreName = (TextView) findViewById(R.id.text_musicDetailPrimaryGenreName);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            Picasso.get()
                    .load(bundle.getString("ArtworkUrl100"))
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imageArtworkUrl100);

            UriMusic = Uri.parse(bundle.getString("PreviewUrl"));
            textArtistName.setText(bundle.getString("ArtistName"));
            textTrackName.setText(bundle.getString("TrackName"));

            int time = Integer.valueOf(bundle.getString("TrackTimeMillis"));
            int topSaniye = time / 1000;
            int dakika = topSaniye/ 60;
            int saniye = topSaniye % 60;
            if(saniye < 10)
                textTrackTimeMillis.setText(dakika + ":0" + saniye);
            else
                textTrackTimeMillis.setText(dakika + ":" + saniye);

            textTrackPrice.setText(bundle.getString("TrackPrice"));
            textReleaseDate.setText(bundle.getString("ReleaseDate").substring(0,4));
            textPrimaryGenreName.setText(bundle.getString("PrimaryGenreName"));
        }

        try {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            mediaPlayer.setDataSource(getApplicationContext(), UriMusic);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                imagePlay.setVisibility(View.GONE);
                imagePause.setVisibility(View.VISIBLE);
            }
        });

        imagePause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                imagePlay.setVisibility(View.VISIBLE);
                imagePause.setVisibility(View.GONE);
            }
        });
    }
}