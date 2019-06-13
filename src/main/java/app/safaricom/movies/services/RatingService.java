package app.safaricom.movies.services;

import app.safaricom.movies.dto.RatingDto;
import app.safaricom.movies.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingDto> getAllRatingDtos()
    {
        List<RatingDto> ratingDtos = new ArrayList<>();

        this.ratingRepository.findAll().forEach(rating -> {
            ratingDtos.add(new RatingDto(rating.getId(), rating.getRating()));
        });

        return  ratingDtos;
    }
}
