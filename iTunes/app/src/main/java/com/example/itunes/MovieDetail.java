package com.example.itunes;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    ImageView imageArtworkUrl100;
    TextView textArtistName, textTrackName, textTrackTimeMillis, textTrackPrice, textReleaseDate, textLongDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageArtworkUrl100 = (ImageView) findViewById(R.id.image_movieDetailArtworkUrl100);

        textArtistName = (TextView) findViewById(R.id.text_movieDetailArtistName);
        textTrackName = (TextView) findViewById(R.id.text_movieTrackName);
        textTrackTimeMillis = (TextView) findViewById(R.id.text_movieDetailTrackTimeMillis);
        textTrackPrice = (TextView) findViewById(R.id.text_movieDetailTrackPrice);
        textReleaseDate = (TextView) findViewById(R.id.text_movieDetailReleaseDate);
        textLongDescription = (TextView) findViewById(R.id.text_movieDetailLongDescription);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            Picasso.get()
                    .load(bundle.getString("ArtworkUrl100"))
                    .fit()
                    .placeholder(R.drawable.loading)
                    .centerCrop()
                    .into(imageArtworkUrl100);

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
            textLongDescription.setText(bundle.getString("LongDescription"));
        }
    }
}