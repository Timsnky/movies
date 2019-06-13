package app.safaricom.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class MovieUserDto {

    private Integer id;

    private String name;
}
