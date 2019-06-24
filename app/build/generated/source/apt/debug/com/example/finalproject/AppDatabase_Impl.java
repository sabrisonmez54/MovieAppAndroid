package com.example.finalproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile MovieDao _movieDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `movie_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `info` TEXT, `director` TEXT, `details` TEXT, `cast` TEXT, `run_time` TEXT, `cast_link` TEXT, `ticket_link` TEXT, `title_link` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"35cdb65334976229fc1785e3298b9e6b\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `movie_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMovieTable = new HashMap<String, TableInfo.Column>(10);
        _columnsMovieTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMovieTable.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsMovieTable.put("info", new TableInfo.Column("info", "TEXT", false, 0));
        _columnsMovieTable.put("director", new TableInfo.Column("director", "TEXT", false, 0));
        _columnsMovieTable.put("details", new TableInfo.Column("details", "TEXT", false, 0));
        _columnsMovieTable.put("cast", new TableInfo.Column("cast", "TEXT", false, 0));
        _columnsMovieTable.put("run_time", new TableInfo.Column("run_time", "TEXT", false, 0));
        _columnsMovieTable.put("cast_link", new TableInfo.Column("cast_link", "TEXT", false, 0));
        _columnsMovieTable.put("ticket_link", new TableInfo.Column("ticket_link", "TEXT", false, 0));
        _columnsMovieTable.put("title_link", new TableInfo.Column("title_link", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieTable = new TableInfo("movie_table", _columnsMovieTable, _foreignKeysMovieTable, _indicesMovieTable);
        final TableInfo _existingMovieTable = TableInfo.read(_db, "movie_table");
        if (! _infoMovieTable.equals(_existingMovieTable)) {
          throw new IllegalStateException("Migration didn't properly handle movie_table(com.example.finalproject.MovieItem).\n"
                  + " Expected:\n" + _infoMovieTable + "\n"
                  + " Found:\n" + _existingMovieTable);
        }
      }
    }, "35cdb65334976229fc1785e3298b9e6b", "234b1e86726e8c13d617c7f1f3d21e7c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "movie_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `movie_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MovieDao movieDao() {
    if (_movieDao != null) {
      return _movieDao;
    } else {
      synchronized(this) {
        if(_movieDao == null) {
          _movieDao = new MovieDao_Impl(this);
        }
        return _movieDao;
      }
    }
  }
}
