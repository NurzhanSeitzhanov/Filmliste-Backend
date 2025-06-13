package de.htwberlin.webtech.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private String genre;
    private Double rating;
    private boolean watched;
    private boolean favorite;

    public Movie() {}

    public Movie(Long id, String title, Integer year, String genre, Double rating, boolean watched, boolean favorite) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.watched = watched;
        this.favorite = favorite;
    }


}

