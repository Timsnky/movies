package app.safaricom.movies.services;

import app.safaricom.movies.dto.MovieDto;
import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.model.Movie;
import app.safaricom.movies.model.Rating;
import app.safaricom.movies.repository.MovieRepository;
import app.safaricom.movies.repository.RatingRepository;
import app.safaricom.movies.requests.StoreMovieRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    private RatingRepository ratingRepository;

    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<MovieDto> getAllMovies()
    {
        List<MovieDto> movieDtos = new ArrayList<>();

        this.movieRepository.findAll().forEach(movie -> {
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
                        movie.getRating().getRating()));
    }

    public Movie store(StoreMovieRequest movieRequest, Integer id)
    {
        if (id != null && !this.checkMovieExistsById(id)) {
            throw new EntityNotFoundException("Movie record was not found for parameter : Id = " + id);
        }

        Rating newRating = this.ratingRepository.findById(movieRequest.getRating())
                .orElseThrow(() ->
                        new EntityNotFoundException("Rating level was not found for parameter : Id = " +
                                movieRequest.getRating())
                );

        Movie movie = new Movie()
                .setId(id)
                .setTitle(movieRequest.getTitle())
                .setDescription(movieRequest.getDescription())
                .setRecommendation(movieRequest.getRecommendation())
                .setWatched(movieRequest.getWatched())
                .setRating(newRating);

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
}
