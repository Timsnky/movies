package app.safaricom.movies.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "A data transfer object to receive the login credentials before authentication")
public class LoginDto {

    @ApiModelProperty(notes = "The login email supplied by the user")
    private String email;

    @ApiModelProperty(notes = "The login password supplied by the user")
    private String password;
}
