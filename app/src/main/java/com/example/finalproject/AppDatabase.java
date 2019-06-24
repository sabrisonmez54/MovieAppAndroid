package com.example.finalproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;


@Database(entities = {MovieItem.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract MovieDao movieDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "movie_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback()
            {

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {

        private final MovieDao mDao;
        // Get the resources from the XML file.
        String[] movieArray =  {"Avengers Endgame",
        "Spider-Man: Far From Home",
        "John Wick: Chapter 3 - Parabellum",
        "Godzilla: King Of The Monsters",
        "Men In Black International"};

        String[] movieInfo =  {"The grave course of events set in motion by Thanos that wiped out half the universe " +
                "and fractured the Avengers ranks compels the remaining Avengers to take one final stand",
                "Following the events of Avengers: Endgame, Spider-Man must step up to take on new" +
                        " threats in a world that has changed forever.",
                "In this third installment of the adrenaline-fueled action franchise, super-assassin" +
                        " John Wick (Keanu Reeves) returns with a $14 million price tag on his head and an army of" +
                        " bounty-hunting killers on his trail.",
                "A face off against a battery of god-sized monsters, including the mighty Godzilla, " +
                        "who collides with Mothra, Rodan, and his ultimate nemesis, the three-headed King Ghidorah",
                "The Men In Black tackle their biggest, most global threat to date:" +
                        " a mole in the Men in Black organization."};

        Integer[] movieImageResources = {R.drawable.img_avengers,
                R.drawable.img_spiderman,R.drawable.img_johnwick,R.drawable.img_godzilla,R.drawable.img_meninblack};

        String[] movieDirector = {"Anthony Russo, Joe Russo",
                "Jon Watts",
                "Chad Stahelski",
                "Michael Dougherty",
                "F. Gary Gray"};
        String[] movieCast = {
                "Robert Downey Jr.\n" +
                "\nChris Evans\n" +
                "\nMark Ruffalo\n" +
                "\nChris Hemsworth\n" +
                "\nScarlett Johansson\n" +
                "\nJeremy Renner\n" +
                "\nSee LINK below for full cast!",

                "Tom Holland\n" +
                        "\nSamuel L. Jackson\n" +
                        "\nJake Gyllenhaal\n" +
                        "\nJon Favreau\n" +
                        "\nZendaya\n" +
                        "\nSee LINK below for full cast!",

                " Keanu Reeves\n" +
                        "\nHalle Berry\n" +
                        "\nnIan McShane\n" +
                        "\nLaurence Fishburne\n" +
                        "\nMark Dacascos\n" +
                        "\nSee LINK below for full cast!",

                "Kyle Chandler\n" +
                        "\nVera Farmiga\n" +
                        "\nMillie Bobby Brown\n" +
                        "\nKen Watanabe\n" +
                        "\nZiyi Zhang\n" +
                        "\nSee LINK below for full cast!",

                "Chris Hemsworth\n" +
                        "\nTessa Thompson\n" +
                        "\nLiam Neeson\n" +
                        "\nRebecca Ferguson\n" +
                        "\nRafe Spall\n" +
                        "\nSee LINK below for full cast!"};
        String[] movieDetails =  {"Avengers Endgame long description",
                "Spider-Man: Far From Home",
                "John Wick: Chapter 3 - Parabellum",
                "Godzilla: King Of The Monsters",
                "Men In Black International"};
        String[] movieRunTime =  {"3 HR 2 MIN",
                "2 HR 10 MIN",
                "2 HR 10 MIN",
                "2 HR 12 MIN",
                "1 HR 55 MIN"};
        String[] movieCastLink =  {"https://www.imdb.com/title/tt4154796/fullcredits",
                "https://www.imdb.com/title/tt6320628/fullcredits/",
                "https://www.imdb.com/title/tt6146586/fullcredits/",
                "https://www.imdb.com/title/tt3741700/fullcredits/",
                "https://www.imdb.com/title/tt2283336/fullcredits/"};
        String[] movieTicketLink =  {"https://www.amctheatres.com/movies/avengers-endgame-45840/showtimes",
                "https://www.amctheatres.com/movies/spider-man-far-from-home-52667/showtimes",
                "https://www.amctheatres.com/movies/john-wick-chapter-3-parabellum-54963/showtimes",
                "https://www.amctheatres.com/movies/godzilla-king-of-the-monsters-46113",
                "https://www.amctheatres.com/movies/men-in-black-international-55120"};

        PopulateDbAsync(AppDatabase db)
        {
            mDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            // Create the ArrayList of Movie objects with titles and information about each sport.
            for(int i = 0; i < movieArray.length; i++)
            {
                MovieItem movieItem = new MovieItem(i, movieArray[i],movieInfo[i],
                        movieImageResources[i],
                        movieDirector[i], movieDetails[i], movieCast[i], movieRunTime[i],
                        movieCastLink[i], movieTicketLink[i]);

               mDao.insert(movieItem);
            }
            return null;
        }
    }
}


