package com.example.finalproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movie_table")
class MovieItem {

    // Member variables representing the title and information about the sport.
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "director")
    private String director;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "cast")
    private String cast;

    @ColumnInfo(name = "run_time")
    private String runTime;

    @ColumnInfo(name = "cast_link")
    private String castLink;

    @ColumnInfo(name = "ticket_link")
    private String ticketLink;

    @ColumnInfo(name = "title_link")
    private final int imageResource;

    public MovieItem(int id, String title, String info, int imageResource,
                    String director, String details,
                    String cast, String runTime, String castLink, String ticketLink) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.director = director;
        this.details = details;
        this.cast = cast;
        this.runTime = runTime;
        this.castLink = castLink;
        this.ticketLink = ticketLink;
    }

    int getId(){return id;}

    int getImageResource()
    {
        return imageResource;
    }

    String getTitle()
    {
        return title;
    }

    String getInfo()
    {
        return info;
    }

    String getDirector()
    {
        return director;
    }

    String getDetails()
    {
        return details;
    }

    String getCast()
    {
        return cast;
    }

    String getRunTime()
    {
        return runTime;
    }

    String getCastLink()
    {
        return castLink;
    }

    String getTicketLink()
    {
        return ticketLink;
    }
}