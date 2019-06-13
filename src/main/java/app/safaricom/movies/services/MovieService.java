package app.safaricom.movies.services;

import app.safaricom.movies.dto.MovieDto;
import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.model.Movie;
import app.safaricom.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
}
