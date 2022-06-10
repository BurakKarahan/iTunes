package com.example.itunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetail extends AppCompatActivity {

    ImageView imageArtworkUrl100;
    TextView textArtistName, textTrackName, textDescription, textGenres, textAverageUserRating, textUserRatingCount, textFormattedPrice;
    RatingBar bookDetailRatinBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        imageArtworkUrl100 = (ImageView) findViewById(R.id.image_bookDetail_artworkUrl100);
        textArtistName = (TextView) findViewById(R.id.text_bookDetailArtistName);
        textTrackName = (TextView) findViewById(R.id.text_bookDetailTrackName);
        textDescription = (TextView) findViewById(R.id.text_bookDetailDescription);
        textGenres = (TextView) findViewById(R.id.text_bookDetailGenres);
        textAverageUserRating = (TextView) findViewById(R.id.text_bookDetailAverageUserRating);
        textUserRatingCount = (TextView) findViewById(R.id.text_bookDetailUserRatingCount);
        textFormattedPrice = (TextView) findViewById(R.id.text_bookDetailFormattedPrice);

        bookDetailRatinBar = (RatingBar) findViewById(R.id.ratingBar);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            if(bundle.getString("ArtworkUrl100") != "Non")
            {
                Picasso.get()
                        .load(bundle.getString("ArtworkUrl100"))
                        .fit()
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .into(imageArtworkUrl100);
            }
            if(!bundle.getString("ArtistName").equals("Non"))
                textArtistName.setText(bundle.getString("ArtistName"));
            if(!bundle.getString("TrackName").equals("Non"))
                textTrackName.setText(bundle.getString("TrackName"));
            if(!bundle.getString("Description").equals("Non"))
                textDescription.setText(bundle.getString("Description"));
            if(!bundle.getString("Genres").equals("Non"))
                textGenres.setText(bundle.getString("Genres"));
            if(!bundle.getString("AverageUserRating").equals("Non"))
                textAverageUserRating.setText(bundle.getString("AverageUserRating"));
            if(!bundle.getString("UserRatingCount").equals("Non"))
                textUserRatingCount.setText(bundle.getString("UserRatingCount") + " Değerlendirme");
            if(!bundle.getString("FormattedPrice").equals("Non"))
                textFormattedPrice.setText(bundle.getString("FormattedPrice"));
            if(!bundle.getString("AverageUserRating").equals("Non"))
                bookDetailRatinBar.setRating(Float.valueOf(bundle.getString("AverageUserRating")));
        }

    }
}