import core.MovieDatabaseImpl;
import models.Movie;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        MovieDatabaseImpl movieDatabase = new MovieDatabaseImpl();

        int count = 1_000;
        for (int i = 0; i < count; i++) {
            Movie movie = new Movie(i + "", "Venom" + i, 1990, 9, List.of("Angel", "Peter"));
            movieDatabase.addMovie(movie);
        }
        movieDatabase.addMovie(new Movie("a", "Me and you", 1993, 11.3, List.of("Jhonny", "Peter")));
        movieDatabase.addMovie(new Movie("b", "Me and me", 1993, 11.2, List.of("Robert", "Jason")));
        movieDatabase.addMovie(new Movie("c", "Schizophrenia", 1991, 1.4, List.of("JJ", "Jhonny")));

        AtomicInteger counter = new AtomicInteger();
        movieDatabase.getMoviesByActors(List.of("Peter", "Angel")).forEach(m -> counter.addAndGet(1));

        System.out.println(counter.get());
    }
}