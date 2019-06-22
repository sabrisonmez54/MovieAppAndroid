package com.example.finalproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private List<MovieItem> mMovieArray;
    private MovieListAdapter mAdapter;

    //Database
    private MovieViewModel mMovieViewModel;

    //FOR SHARED PREFERENCES
    private SharedPreferences mPreferences;
    private final String sharedPrefFile = "com.example.android.FinalProjectSharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        mMovieArray = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new MovieListAdapter(this, mMovieArray);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        //initializeData();

        //FOR SHARED PREFERENCES
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        android.support.v7.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        mPreferences = android.support.v7.preference.PreferenceManager
                .getDefaultSharedPreferences(this);

        //getting viewModel of database
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        //observer for the live data
        mMovieViewModel.getmAllMovies().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable final List<MovieItem> movies) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setMovies(movies);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Menu Options
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//COMMENTED OUT AS THE DATA IS NOW INITIALIZED IN ROOM DATABASE
//    //Initialize movie data from resource
//    private void initializeData() {
//        // Get the resources from the XML file.
//        String[] movieArray = getResources()
//                .getStringArray(R.array.array_movieTitles);
//        String[] movieInfo = getResources()
//                .getStringArray(R.array.array_movieInfo);
//        TypedArray movieImageResources =
//                getResources().obtainTypedArray(R.array.array_moviePhotos);
//        String[] movieDirector = getResources()
//                .getStringArray(R.array.array_movieDirector);
//        String[] movieDetails = getResources()
//                .getStringArray(R.array.array_movieDetails);
//        String[] movieCast = getResources()
//                .getStringArray(R.array.array_movieCast);
//        String[] movieRunTime = getResources()
//                .getStringArray(R.array.array_movieRunTimes);
//        String[] movieCastLink = getResources()
//                .getStringArray(R.array.array_castLinks);
//        String[] movieTicketLink = getResources()
//                .getStringArray(R.array.array_ticketLink);
//
//        // Clear the existing data to avoid duplication
//        mMovieArray.clear();
//
//        // Create the ArrayList of Movie objects with titles and information about each sport.
//        for(int i = 0; i < movieArray.length; i++){
//            mMovieArray.add(new MovieItem(movieArray[i],movieInfo[i],
//                    movieImageResources.getResourceId(i,0),
//                    movieDirector[i], movieDetails[i], movieCast[i], movieRunTime[i],
//                    movieCastLink[i], movieTicketLink[i]));
//        }
//
//        movieImageResources.recycle();
//
//        // Notify the adapter of the change.
//        mAdapter.notifyDataSetChanged();
//    }

    //SHARED PREFERENCES
    @Override
    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.apply();
    }

    //SHARED PREFERENCES
    public void reset(View view)
    {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

}
