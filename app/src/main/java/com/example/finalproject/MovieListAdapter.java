package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    // Member variables.
    private List<MovieItem> mMovieArray;
    private Context mContext;
    private SharedPreferences mPreferences;

    MovieListAdapter(Context context, List<MovieItem> movieData) {
        this.mMovieArray = movieData;
        this.mContext = context;
    }
    void setMovies(List<MovieItem> movies){
        mMovieArray = movies;
        notifyDataSetChanged();
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

            // For shared preferences.
            android.support.v7.preference.PreferenceManager
                    .setDefaultValues(mContext, R.xml.preferences, false);
            mPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(mContext);

            Boolean largeText = mPreferences.getBoolean(SettingsActivity.LARGE_TEXT_PREFERENCE, false);
            if (largeText) {
                mMovieTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                mInfoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            }

            Boolean nightMode = mPreferences.getBoolean(SettingsActivity.NIGHT_MODE_PREFERENCE, false);
            if (nightMode) {
                mMovieTitle.setTextColor(mContext.getResources().getColor(R.color.white));
            }

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
