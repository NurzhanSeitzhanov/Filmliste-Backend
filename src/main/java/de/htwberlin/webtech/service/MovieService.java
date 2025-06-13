package de.htwberlin.webtech.service;

import de.htwberlin.webtech.repo.MovieRepository;
import de.htwberlin.webtech.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

    public Movie save(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        return repo.save(movie);
    }

    public Movie get(Long id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Movie> getAll() {
        Iterable<Movie> iterator = repo.findAll();
        List<Movie> movies = new ArrayList<>();
        for (Movie movie : iterator) {
            movies.add(movie);
        }
        return movies;
    }

    public Movie update(Long id, Movie movieData) {
        Movie movie = repo.findById(id).orElseThrow(RuntimeException::new);

        if (movieData.getTitle() == null || movieData.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }

        movie.setTitle(movieData.getTitle());
        movie.setYear(movieData.getYear());
        movie.setGenre(movieData.getGenre());
        movie.setRating(movieData.getRating());
        movie.setWatched(movieData.isWatched());
        movie.setFavorite(movieData.isFavorite());

        return repo.save(movie);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
