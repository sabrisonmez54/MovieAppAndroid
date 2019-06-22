package com.example.finalproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository
{
    private MovieDao mMovieDao;
    private LiveData<List<MovieItem>> mAllMovies;

    MovieRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    LiveData<List<MovieItem>> getAllMovies()
    {
        return mAllMovies;
    }

    public void insert (MovieItem movieItem)
    {
        new insertAsyncTask(mMovieDao).execute(movieItem);
    }

    private static class insertAsyncTask extends AsyncTask<MovieItem, Void, Void>
    {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieItem... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
