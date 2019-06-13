package app.safaricom.movies.repository;

import app.safaricom.movies.model.Movie;
import app.safaricom.movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public List<Movie> findMovieByWatchedAndUser(Integer watched, User user);

    public List<Movie> findMovieByUser(User user);
}
