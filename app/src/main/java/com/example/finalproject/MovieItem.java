package com.example.finalproject;

class MovieItem {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private String director;
    private String details;
    private String cast;
    private String runTime;
    private String castLink;
    private String ticketLink;
    private final int imageResource;

    public MovieItem(String title, String info, int imageResource,
                    String director, String details,
                    String cast, String runTime, String castLink, String ticketLink) {
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