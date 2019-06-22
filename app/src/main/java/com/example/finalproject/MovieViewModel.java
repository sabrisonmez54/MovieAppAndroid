package com.example.finalproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel
{

        private MovieRepository mRepository;

        private LiveData<List<MovieItem>> mAllMovies;

        public MovieViewModel (Application application)
        {
            super(application);
            mRepository = new MovieRepository(application);
            mAllMovies = mRepository.getAllMovies();
        }

        LiveData<List<MovieItem>> getmAllMovies() { return mAllMovies; }

        public void insert(MovieItem movieItem) { mRepository.insert(movieItem); }
}
