package app.safaricom.movies.controller;

import app.safaricom.movies.dto.MovieDto;
import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.requests.StoreMovieRequest;
import app.safaricom.movies.services.MovieService;
import app.safaricom.movies.services.RatingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation(value = "Stores or Updates a movie record",
            notes = "The path variable id is optional. When not provided a create operation will be done," +
                    " if provided an update operation will be done",
            response = MovieDto.class)
    @PostMapping(value = {"/store", "/store/{id}"})
    public ResponseEntity<MovieDto> store(@PathVariable(required = false) Integer id,
                                         @RequestBody @Valid StoreMovieRequest movieRequest)
    {
        MovieDto movieDto = this.movieService.getMovieDtoFromMovie(this.movieService.store(movieRequest, id));

        return new ResponseEntity<MovieDto>(movieDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Gets the details of the movie based on the supplied id",
            response = MovieDto.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> details(@PathVariable(required = true) Integer id)
    {
        MovieDto movieDto = this.movieService.getMovieDtoFromMovie(this.movieService.getMovie(id));

        return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the movie based on the supplied id",
            response = MovieDto.class)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(required = true) Integer id)
    {
        this.movieService.deleteMovie(id);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
