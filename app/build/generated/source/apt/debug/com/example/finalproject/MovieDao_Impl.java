package com.example.finalproject;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMovieItem;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMovieItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieItem = new EntityInsertionAdapter<MovieItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `movie_table`(`id`,`title`,`info`,`director`,`details`,`cast`,`run_time`,`cast_link`,`ticket_link`,`title_link`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getInfo());
        }
        if (value.getDirector() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDirector());
        }
        if (value.getDetails() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDetails());
        }
        if (value.getCast() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCast());
        }
        if (value.getRunTime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRunTime());
        }
        if (value.getCastLink() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCastLink());
        }
        if (value.getTicketLink() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTicketLink());
        }
        stmt.bindLong(10, value.getImageResource());
      }
    };
    this.__deletionAdapterOfMovieItem = new EntityDeletionOrUpdateAdapter<MovieItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `movie_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM movie_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(MovieItem movieItem) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovieItem.insert(movieItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMovie(MovieItem movieItem) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMovieItem.handle(movieItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<MovieItem>> getAllMovies() {
    final String _sql = "SELECT * from movie_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<MovieItem>>() {
      private Observer _observer;

      @Override
      protected List<MovieItem> compute() {
        if (_observer == null) {
          _observer = new Observer("movie_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
          final int _cursorIndexOfDirector = _cursor.getColumnIndexOrThrow("director");
          final int _cursorIndexOfDetails = _cursor.getColumnIndexOrThrow("details");
          final int _cursorIndexOfCast = _cursor.getColumnIndexOrThrow("cast");
          final int _cursorIndexOfRunTime = _cursor.getColumnIndexOrThrow("run_time");
          final int _cursorIndexOfCastLink = _cursor.getColumnIndexOrThrow("cast_link");
          final int _cursorIndexOfTicketLink = _cursor.getColumnIndexOrThrow("ticket_link");
          final int _cursorIndexOfImageResource = _cursor.getColumnIndexOrThrow("title_link");
          final List<MovieItem> _result = new ArrayList<MovieItem>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MovieItem _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpInfo;
            _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            final String _tmpDirector;
            _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
            final String _tmpDetails;
            _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
            final String _tmpCast;
            _tmpCast = _cursor.getString(_cursorIndexOfCast);
            final String _tmpRunTime;
            _tmpRunTime = _cursor.getString(_cursorIndexOfRunTime);
            final String _tmpCastLink;
            _tmpCastLink = _cursor.getString(_cursorIndexOfCastLink);
            final String _tmpTicketLink;
            _tmpTicketLink = _cursor.getString(_cursorIndexOfTicketLink);
            final int _tmpImageResource;
            _tmpImageResource = _cursor.getInt(_cursorIndexOfImageResource);
            _item = new MovieItem(_tmpId,_tmpTitle,_tmpInfo,_tmpImageResource,_tmpDirector,_tmpDetails,_tmpCast,_tmpRunTime,_tmpCastLink,_tmpTicketLink);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public MovieItem[] getAnyMovie() {
    final String _sql = "SELECT * from movie_table LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
      final int _cursorIndexOfDirector = _cursor.getColumnIndexOrThrow("director");
      final int _cursorIndexOfDetails = _cursor.getColumnIndexOrThrow("details");
      final int _cursorIndexOfCast = _cursor.getColumnIndexOrThrow("cast");
      final int _cursorIndexOfRunTime = _cursor.getColumnIndexOrThrow("run_time");
      final int _cursorIndexOfCastLink = _cursor.getColumnIndexOrThrow("cast_link");
      final int _cursorIndexOfTicketLink = _cursor.getColumnIndexOrThrow("ticket_link");
      final int _cursorIndexOfImageResource = _cursor.getColumnIndexOrThrow("title_link");
      final MovieItem[] _result = new MovieItem[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final MovieItem _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpInfo;
        _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
        final String _tmpDirector;
        _tmpDirector = _cursor.getString(_cursorIndexOfDirector);
        final String _tmpDetails;
        _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
        final String _tmpCast;
        _tmpCast = _cursor.getString(_cursorIndexOfCast);
        final String _tmpRunTime;
        _tmpRunTime = _cursor.getString(_cursorIndexOfRunTime);
        final String _tmpCastLink;
        _tmpCastLink = _cursor.getString(_cursorIndexOfCastLink);
        final String _tmpTicketLink;
        _tmpTicketLink = _cursor.getString(_cursorIndexOfTicketLink);
        final int _tmpImageResource;
        _tmpImageResource = _cursor.getInt(_cursorIndexOfImageResource);
        _item = new MovieItem(_tmpId,_tmpTitle,_tmpInfo,_tmpImageResource,_tmpDirector,_tmpDetails,_tmpCast,_tmpRunTime,_tmpCastLink,_tmpTicketLink);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
