package app.safaricom.movies.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MovieDto {

    private Integer id;

    private String title;

    private String description;

    private String recommendation;

    private RatingDto rating;

    private Integer watched;

    private String watchedName;
}
