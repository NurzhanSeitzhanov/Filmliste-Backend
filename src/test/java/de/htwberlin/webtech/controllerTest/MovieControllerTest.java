package de.htwberlin.webtech.controllerTest;

import de.htwberlin.webtech.controller.MovieController;
import de.htwberlin.webtech.model.Movie;
import de.htwberlin.webtech.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    void testGetMovieById() throws Exception {
        Movie movie = new Movie(1L, "Inception", 2010, "Sci-Fi", 8.8, true, true);

        when(movieService.get(1L)).thenReturn(movie);

        String expected = """
            {"id":1,"title":"Inception","year":2010,"genre":"Sci-Fi","rating":8.8,"watched":true,"favorite":true}
            """;

        this.mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void testGetAllMovies() throws Exception {
        Movie movie1 = new Movie(1L, "Inception", 2010, "Sci-Fi", 8.8, true, true);
        Movie movie2 = new Movie(2L, "Interstellar", 2014, "Sci-Fi", 8.6, true, false);

        when(movieService.getAll()).thenReturn(List.of(movie1, movie2));

        String expected = """
            [
              {"id":1,"title":"Inception","year":2010,"genre":"Sci-Fi","rating":8.8,"watched":true,"favorite":true},
              {"id":2,"title":"Interstellar","year":2014,"genre":"Sci-Fi","rating":8.6,"watched":true,"favorite":false}
            ]
            """;

        this.mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void testCreateMovie() throws Exception {
        Movie movie = new Movie(1L, "Inception", 2010, "Sci-Fi", 8.8, true, true);

        when(movieService.save(any(Movie.class))).thenReturn(movie);

        String requestBody = """
            {"title":"Inception","year":2010,"genre":"Sci-Fi","rating":8.8,"watched":true,"favorite":true}
            """;

        String expected = """
            {"id":1,"title":"Inception","year":2010,"genre":"Sci-Fi","rating":8.8,"watched":true,"favorite":true}
            """;

        this.mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void testUpdateMovie() throws Exception {
        Movie updatedMovie = new Movie(1L, "Inception Updated", 2010, "Sci-Fi", 9.0, true, true);

        when(movieService.update(any(Long.class), any(Movie.class))).thenReturn(updatedMovie);

        String requestBody = """
            {"title":"Inception Updated","year":2010,"genre":"Sci-Fi","rating":9.0,"watched":true,"favorite":true}
            """;

        String expected = """
            {"id":1,"title":"Inception Updated","year":2010,"genre":"Sci-Fi","rating":9.0,"watched":true,"favorite":true}
            """;

        this.mockMvc.perform(put("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieService).delete(1L);

        this.mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isOk());
    }
}
