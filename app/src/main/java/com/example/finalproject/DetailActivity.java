package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if(ab != null)
        {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        //This creates the Floating Action Button that will redirect
        //  the user to AMC's ticket buying page for the selected movie
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(DetailActivity.this);

                myAlertBuilder.setTitle("Buy Tickets?");
                myAlertBuilder.setMessage("You are about to leave the app. Continue?");
                myAlertBuilder.setPositiveButton("OK", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String url = getIntent().getStringExtra("ticketLink");
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }});
                myAlertBuilder.setNegativeButton("No", null);
                myAlertBuilder.show();
            }
        });

        // Initialize the views.
        TextView movieTitle = findViewById(R.id.movieTitleDetail);
        ImageView movieImage = findViewById(R.id.movieImageDetail);
        TextView movieDirector = findViewById(R.id.movieDirectorDetail);
        TextView movieDetail = findViewById(R.id.movieDescriptionDetail);
        TextView movieCast = findViewById(R.id.movieCastDetail);
        TextView movieRunTime = findViewById(R.id.movieRunTimeDetail);
        TextView movieCastLink = findViewById(R.id.movieCastLinksDetail);

        // Set the text from the Intent extra.
        movieTitle.setText(getIntent().getStringExtra("title"));
        movieImage.setImageResource(getIntent().getIntExtra("image_resource", 0));
        movieDirector.setText(getIntent().getStringExtra("director"));
        movieDetail.setText(getIntent().getStringExtra("details"));
        movieCast.setText(getIntent().getStringExtra("cast"));
        movieRunTime.setText(getIntent().getStringExtra("runTime"));
        movieCastLink.setText(getIntent().getStringExtra("castLink"));

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(movieImage);

        // For shared preferences.
        android.support.v7.preference.PreferenceManager
                .setDefaultValues(this, R.xml.preferences, false);
        mPreferences = android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this);

        Boolean nightMode = mPreferences.getBoolean(SettingsActivity.NIGHT_MODE_PREFERENCE, false);
        if (nightMode) {
            movieTitle.setTextColor(getResources().getColor(R.color.white));
        }
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
}
