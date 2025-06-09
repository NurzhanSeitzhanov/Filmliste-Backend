package de.htwberlin.webtech.serviceTest;

import de.htwberlin.webtech.model.Movie;
import de.htwberlin.webtech.repo.MovieRepository;
import de.htwberlin.webtech.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @MockitoBean
    private MovieRepository movieRepository;

    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movie1 = new Movie(1L, "Inception", 2010, "Sci-Fi", 8.8, true, true);
        movie2 = new Movie(2L, "Interstellar", 2014, "Sci-Fi", 8.6, true, false);
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));

        List<Movie> movies = movieService.getAll();

        assertEquals(2, movies.size());
    }

    @Test
    void testGetMovieById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie1));

        Movie result = movieService.get(1L);

        assertNotNull(result);
    }

    @Test
    void testSaveMovie() {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie1);

        Movie savedMovie = movieService.save(movie1);

        assertEquals("Inception", savedMovie.getTitle());
        verify(movieRepository, times(1)).save(movie1);
    }

    @Test
    void testUpdateMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie1));
        when(movieRepository.save(any(Movie.class))).thenReturn(movie1);

        Movie updatedData = new Movie(null, "Inception Updated", 2010, "Sci-Fi", 9.0, true, true);

        Movie updatedMovie = movieService.update(1L, updatedData);

        assertEquals("Inception Updated", updatedMovie.getTitle());
        assertEquals(9.0, updatedMovie.getRating());
        verify(movieRepository, times(1)).save(movie1);
    }

    @Test
    void testDeleteMovie() {
        doNothing().when(movieRepository).deleteById(1L);

        movieService.delete(1L);

        verify(movieRepository, times(1)).deleteById(1L);
    }
}
