package app.safaricom.movies.controller;

import app.safaricom.movies.dto.MovieDto;
import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.services.MovieService;
import app.safaricom.movies.services.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Api(value = "Movie Operations Controller")
public class MovieController {

    private RatingService ratingService;

    private MovieService movieService;

    public MovieController(RatingService ratingService, MovieService movieService) {
        this.ratingService = ratingService;
        this.movieService = movieService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_User')")
    @ApiOperation(value = "Gets a list of all movies",
            response = MovieDto.class,
            responseContainer = "List", authorizations = {@Authorization(value = "ROLE_User")})
    public ResponseEntity<List> index()
    {
        List<MovieDto> movieDtos = this.movieService.getAllMovies();

        return  new ResponseEntity<List>(movieDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets a list of all movie rating levels",
            response = RatingDto.class,
            responseContainer = "List")
    @GetMapping(value = "/ratings")
    public ResponseEntity<List> getRatings()
    {
        return new ResponseEntity<List>(
                this.ratingService.getAllRatingDtos(),
                HttpStatus.OK
        );
    }

}
