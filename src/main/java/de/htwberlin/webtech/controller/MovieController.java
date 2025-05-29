package de.htwberlin.webtech.controller;

import de.htwberlin.webtech.MovieService;
import de.htwberlin.webtech.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    Logger logger = LoggerFactory.getLogger(MovieController.class);

    // Create a new movie
    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        logger.info("POST /movies - Creating movie: {}", movie);
        return movieService.save(movie);
    }

    // Get all movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        logger.info("GET /movies - Fetching all movies");
        return movieService.getAll();
    }

    // Get a movie by ID
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        logger.info("GET /movies/{} - Fetching movie by ID", id);
        return movieService.get(id);
    }

    // Update a movie
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        logger.info("PUT /movies/{} - Updating movie: {}", id, movie);
        return movieService.update(id, movie);
    }

    // Delete a movie
    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        logger.info("DELETE /movies/{} - Deleting movie", id);
        movieService.delete(id);
    }

}
