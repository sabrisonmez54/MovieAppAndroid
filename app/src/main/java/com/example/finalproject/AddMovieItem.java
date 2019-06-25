package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMovieItem extends AppCompatActivity {

    private EditText mTitle;
    private EditText mInfo;

    public static final String EXTRA_TITLE = "com.example.finalproject.extra_title";
    public static final String EXTRA_INFO = "com.example.finalproject.extra_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_item);

        mTitle = findViewById(R.id.wishlist_title);
        mInfo = findViewById(R.id.wishlist_info);
    }

    public void addMovie(View view) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(mTitle.getText()) || TextUtils.isEmpty(mInfo.getText())) {
            setResult(RESULT_CANCELED, intent);
        } else {
            String title = mTitle.getText().toString();
            intent.putExtra(EXTRA_TITLE, title);
            String info = mInfo.getText().toString();
            intent.putExtra(EXTRA_INFO, info);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
