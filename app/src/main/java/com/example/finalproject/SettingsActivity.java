package com.example.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity
{

    public static final String NIGHT_MODE_PREFERENCE = "night_mode";
    public static final String NICKNAME_PREFERENCE = "name";
    public static final String LARGE_TEXT_PREFERENCE = "large_description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
