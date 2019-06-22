package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<MovieItem> mMovieArray;
    private Context mContext;

    MovieListAdapter(Context context, ArrayList<MovieItem> movieData) {
        this.mMovieArray = movieData;
        this.mContext = context;
    }


    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.movielist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder,
                                 int position) {

        MovieItem currentMovie = mMovieArray.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentMovie);
    }

    @Override
    public int getItemCount() {
        return mMovieArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mMovieTitle;
        private TextView mInfoText;
        private ImageView mMovieImage;
        private TextView mMovieDirector;
        private TextView mMovieDetails;
        private TextView mMovieCast;
        private TextView mMovieRunTime;
        private TextView mMovieCastLinks;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mMovieTitle = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mMovieImage = itemView.findViewById(R.id.movieImages);
            mMovieDirector = itemView.findViewById(R.id.movieDirectorDetail);
            mMovieDetails = itemView.findViewById(R.id.movieDescriptionDetail);
            mMovieCast = itemView.findViewById(R.id.movieCastDetail);
            mMovieRunTime = itemView.findViewById(R.id.movieRunTimeDetail);
            mMovieCastLinks = itemView.findViewById(R.id.movieCastLinksDetail);

            itemView.setOnClickListener(this);
        }

        void bindTo(MovieItem currentMovie) {
            // Populate the textviews with data.
            mMovieTitle.setText(currentMovie.getTitle());
            mInfoText.setText(currentMovie.getInfo());

            Glide.with(mContext).load(currentMovie.getImageResource()).into(mMovieImage);

        }

        @Override
        public void onClick(View view) {
            MovieItem currentMovie = mMovieArray.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);

            detailIntent.putExtra("title", currentMovie.getTitle());
            detailIntent.putExtra("image_resource", currentMovie.getImageResource());
            detailIntent.putExtra("director", currentMovie.getDirector());
            detailIntent.putExtra("details", currentMovie.getDetails());
            detailIntent.putExtra("cast", currentMovie.getCast());
            detailIntent.putExtra("runTime", currentMovie.getRunTime());
            detailIntent.putExtra("castLink", currentMovie.getCastLink());
            detailIntent.putExtra("ticketLink", currentMovie.getTicketLink());
            mContext.startActivity(detailIntent);

        }
    }
}
