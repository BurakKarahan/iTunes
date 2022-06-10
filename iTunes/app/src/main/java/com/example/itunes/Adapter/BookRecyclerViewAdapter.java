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

import com.example.itunes.BookDetail;
import com.example.itunes.Model.BookResult;
import com.example.itunes.Model.MusicResult;
import com.example.itunes.MovieDetail;
import com.example.itunes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.RowHolder> {

    private ArrayList<BookResult> bookResultList;
    Context contex;
    Activity activity;

    public BookRecyclerViewAdapter(ArrayList<BookResult> bookResultList, Context contex, Activity activity) {
        this.bookResultList = bookResultList;
        this.contex = contex;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_book,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerViewAdapter.RowHolder holder, int position) {
        holder.bind(bookResultList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return bookResultList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayoutBook;
        ImageView imgBookArtworkUrl100;
        TextView textBookArtistName, textBookTrackName, textBookGenres, textBookPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final BookResult bookResultModel, Integer position) {

            String genres = "";

            linearLayoutBook = (LinearLayout) itemView.findViewById(R.id.linearLayout_book);
            imgBookArtworkUrl100 = (ImageView) itemView.findViewById(R.id.image_book_artworkUrl100);
            textBookArtistName = (TextView) itemView.findViewById(R.id.text_book_artistName);
            textBookTrackName = (TextView) itemView.findViewById(R.id.text_book_trackName);
            textBookGenres = (TextView) itemView.findViewById(R.id.text_book_genres);
            textBookPrice = (TextView) itemView.findViewById(R.id.text_book_price);

            if(bookResultModel.getArtworkUrl100() != null)
            {
                Picasso.get()
                        .load(bookResultModel.getArtworkUrl100())
                        .fit()
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .into(imgBookArtworkUrl100);
            }
            if(bookResultModel.getArtistName() != null)
                textBookTrackName.setText(bookResultModel.getArtistName());
            if(bookResultModel.getTrackName() != null)
                textBookArtistName.setText(bookResultModel.getTrackName());
            if(bookResultModel.getGenres() != null)
                textBookGenres.setText(String.valueOf(bookResultModel.getGenres()));
            if(bookResultModel.getFormattedPrice() != null)
                textBookPrice.setText(bookResultModel.getFormattedPrice());

            linearLayoutBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(contex, BookDetail.class);
                    if(bookResultModel.getArtworkUrl100() != null)
                        intent.putExtra("ArtworkUrl100",bookResultList.get(position).getArtworkUrl100());
                    else
                        intent.putExtra("ArtworkUrl100","Non");
                    if(bookResultModel.getCurrency() != null)
                        intent.putExtra("Currency",bookResultList.get(position).getCurrency());
                    else
                        intent.putExtra("Currency","Non");
                    if(bookResultModel.getTrackName() != null)
                        intent.putExtra("TrackName",bookResultList.get(position).getTrackName());
                    else
                        intent.putExtra("TrackName","Non");
                    if(bookResultModel.getDescription() != null)
                        intent.putExtra("Description",bookResultList.get(position).getDescription());
                    else
                        intent.putExtra("Description","Non");
                    if(bookResultModel.getArtistName() != null)
                        intent.putExtra("ArtistName",bookResultList.get(position).getArtistName());
                    else
                        intent.putExtra("ArtistName","Non");
                    if(bookResultModel.getPrice() != null)
                        intent.putExtra("Price",bookResultList.get(position).getPrice());
                    else
                        intent.putExtra("Price","Non");
                    if(bookResultModel.getAverageUserRating() != null)
                        intent.putExtra("AverageUserRating",bookResultList.get(position).getAverageUserRating());
                    else
                        intent.putExtra("AverageUserRating","Non");
                    if(bookResultModel.getUserRatingCount() != null)
                        intent.putExtra("UserRatingCount",bookResultList.get(position).getUserRatingCount());
                    else
                        intent.putExtra("UserRatingCount","Non");
                    if(bookResultModel.getFormattedPrice() != null)
                        intent.putExtra("FormattedPrice",bookResultList.get(position).getFormattedPrice());
                    else
                        intent.putExtra("FormattedPrice","Non");
                    if(bookResultModel.getGenres() != null)
                        intent.putExtra("Genres", String.valueOf(bookResultList.get(position).getGenres()));
                    else
                        intent.putExtra("Genres","Non");
                    activity.startActivity(intent);
                }
            });
        }
    }
}
