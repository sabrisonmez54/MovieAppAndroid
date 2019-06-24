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
    // delete all movies
    private static class deleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private MovieDao mAsyncTaskDao;

        deleteAllMoviesAsyncTask(MovieDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll()
    {
        new deleteAllMoviesAsyncTask(mMovieDao).execute();
    }

    //delete single movie
    private static class deleteMovieAsyncTask extends AsyncTask<MovieItem, Void, Void>
    {
        private MovieDao mAsyncTaskDao;

        deleteMovieAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieItem... params)
        {
            mAsyncTaskDao.deleteMovie(params[0]);
            return null;
        }
    }

    public void deleteMovie(MovieItem movieItem)
    {
        new deleteMovieAsyncTask(mMovieDao).execute(movieItem);
    }
}
