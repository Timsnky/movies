package app.safaricom.movies.requests;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class StoreMovieRequest {

    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotBlank(message = "Description should not be blank")
    private String description;

    @NotBlank(message = "Recommendation should not be blank")
    private String recommendation;

    @NotNull(message = "Rating level should not be null")
    private Integer rating;

    @NotNull(message = "Watched flag should not be null")
    private Integer watched;
}
