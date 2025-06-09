package de.htwberlin.webtech.modelTest;

import de.htwberlin.webtech.model.Movie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieTest {

    @Test
    void testToString() {
        // Eingabedaten
        Long id = 1L;
        String title = "Inception";
        int year = 2010;
        String genre = "Sci-Fi";
        double rating = 8.8;
        boolean watched = true;
        boolean favorite = true;

        // System under test aufsetzen
        Movie movie = new Movie(id, title, year, genre, rating, watched, favorite);

        // Erwartetes Ergebnis
        String expected = "Movie(id=1, title=Inception, year=2010, genre=Sci-Fi, rating=8.8, watched=true, favorite=true)";

        // Tatsächliches Ergebnis
        String actual = movie.toString();

        // Vergleich
        assertEquals(expected, actual);
    }
}
