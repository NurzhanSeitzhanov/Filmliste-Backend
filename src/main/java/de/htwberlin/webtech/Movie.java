package de.htwberlin.webtech;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie {
    private Long id;
    private String title;
    private int year;
    private String genre;
    private double rating;
    private boolean watched;
    private boolean favorite;

    public Movie(Long id, String title, int year, String genre, double rating, boolean watched, boolean favorite) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.watched = watched;
        this.favorite = favorite;
    }

    // Getter & Setter

}

