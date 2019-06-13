package app.safaricom.movies;

import app.safaricom.movies.model.Movie;
import app.safaricom.movies.model.Rating;
import app.safaricom.movies.model.User;
import app.safaricom.movies.repository.MovieRepository;
import app.safaricom.movies.repository.RatingRepository;
import app.safaricom.movies.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testReturnsMovieByUser()
    {
        Rating rating = this.saveRating();
        User user = this.saveUser();

        String movieName = "Test Movie";

        Movie movie = new Movie()
                .setUser(user)
                .setRating(rating)
                .setTitle(movieName);
        entityManager.persistAndFlush(movie);

        List<Movie> foundMovie = movieRepository.findMovieByUser(user);

        foundMovie.forEach(mv -> {
            assertThat(mv.getUser().getId()).isEqualTo(user.getId());
        });
    }

    @Test
    public void testDoesntReturnMovieByDifferentUser()
    {
        Rating rating = this.saveRating();
        User user = this.saveUser();
        User differentUser = this.saveUser();

        String movieName = "Test Movie";

        Movie movie = new Movie()
                .setUser(differentUser)
                .setRating(rating)
                .setTitle(movieName);

        Movie movie2 = new Movie()
                .setUser(user)
                .setRating(rating)
                .setTitle(movieName);

        entityManager.persistAndFlush(movie);

        List<Movie> foundMovie = movieRepository.findMovieByUser(user);

        foundMovie.forEach(mv -> {
            assertThat(mv.getUser().getId()).isNotEqualTo(differentUser.getId());
            assertThat(mv.getUser().getId()).isEqualTo(user.getId());
        });
    }

    @Test
    public void testReturnOnlyWatchedMoviesAndForUser()
    {
        Rating rating = this.saveRating();
        User user = this.saveUser();

        String movieName = "Test Movie";

        Movie movie = new Movie()
                .setUser(user)
                .setRating(rating)
                .setTitle(movieName)
                .setWatched(0);

        Movie movie1 = new Movie()
                .setUser(user)
                .setRating(rating)
                .setWatched(1);

        entityManager.persistAndFlush(movie);

        List<Movie> watchedMovies = movieRepository.findMovieByWatchedAndUser(1, user);

        watchedMovies.forEach(mv -> {
            assertThat(mv.getWatched()).isEqualTo(1);
        });

        List<Movie> notWatchedMovies = movieRepository.findMovieByWatchedAndUser(0, user);

        notWatchedMovies.forEach(mv -> {
            assertThat(mv.getWatched()).isEqualTo(0);
        });
    }

    private Rating saveRating()
    {
        Rating rating = new Rating().setRating(1);
        entityManager.persistAndFlush(rating);

        return rating;
    }

    private User saveUser()
    {
        User user = new User()
                .setEmail("test@example.com")
                .setFirstname("John")
                .setLastname("Done")
                .setPassword("password");

        entityManager.persistAndFlush(user);

        return  user;
    }
}
