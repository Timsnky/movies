package app.safaricom.movies.services;

import app.safaricom.movies.dto.MovieDto;
import app.safaricom.movies.dto.MovieUserDto;
import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.model.Movie;
import app.safaricom.movies.model.Rating;
import app.safaricom.movies.model.User;
import app.safaricom.movies.repository.MovieRepository;
import app.safaricom.movies.repository.RatingRepository;
import app.safaricom.movies.repository.UserRepository;
import app.safaricom.movies.requests.StoreMovieRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    private RatingRepository ratingRepository;

    private UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public List<MovieDto> getAllMovies(int page, int limit)
    {
        Pageable pageable = PageRequest.of(page, limit);

        List<MovieDto> movieDtos = new ArrayList<>();

        this.movieRepository.findAll(pageable).forEach(movie -> {
            movieDtos.add(this.getMovieDtoFromMovie(movie));
        });

        return  movieDtos;
    }

    public MovieDto getMovieDtoFromMovie(Movie movie)
    {
        return  new MovieDto()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setDescription(movie.getDescription())
                .setRecommendation(movie.getRecommendation())
                .setWatched(movie.getWatched())
                .setWatchedName(movie.isWatched())
                .setRating(new RatingDto(
                        movie.getRating().getId(),
                        movie.getRating().getRating()))
                .setUser(new MovieUserDto(
                        movie.getUser().getId(),
                        movie.getUser().getFullname()));

    }

    public Movie store(StoreMovieRequest movieRequest, Integer id)
    {
        if (id != null && !this.checkMovieExistsById(id)) {
            throw new EntityNotFoundException("Movie record was not found for parameter : Id = " + id);
        }

        Rating rating = this.ratingRepository.findById(movieRequest.getRating())
                .orElseThrow(() ->
                        new EntityNotFoundException("Rating level was not found for parameter : Id = " +
                                movieRequest.getRating())
                );

        User user = this.userRepository.findById(movieRequest.getUser())
                .orElseThrow(() ->
                        new EntityNotFoundException("User was not found for parameter : Id = " +
                                movieRequest.getUser())
                );

        Movie movie = new Movie()
                .setId(id)
                .setTitle(movieRequest.getTitle())
                .setDescription(movieRequest.getDescription())
                .setRecommendation(movieRequest.getRecommendation())
                .setWatched(movieRequest.getWatched())
                .setRating(rating)
                .setUser(user);

        return this.movieRepository.save(movie);

    }

    public boolean checkMovieExistsById(Integer id)
    {
        return this.movieRepository.existsById(id);
    }

    public Movie getMovie(Integer id)
    {
        return this.movieRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Movie record was not found for parameter : Id = " + id)
                );
    }

    public void deleteMovie(Integer id)
    {
        this.movieRepository.delete(this.getMovie(id));
    }

    public List<MovieDto> getWatchedMovies(Integer watched, Principal principal)
    {
        String email = principal.getName();

        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new EntityNotFoundException("User record was not found for parameter : Email = " + email);
        }

        List<MovieDto> movieDtos = new ArrayList<>();

        List<Movie> movies = new ArrayList<>();

        if (watched == null) {
            movies = this.movieRepository.findMovieByUser(user);
        } else {
            movies = this.movieRepository.findMovieByWatchedAndUser(watched, user);
        }

        movies.forEach(movie -> {
            movieDtos.add(this.getMovieDtoFromMovie(movie));
        });

        return  movieDtos;
    }
}
