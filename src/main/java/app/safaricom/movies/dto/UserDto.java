package app.safaricom.movies.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(description = "A data transfer object to expose the User entity details")
public class UserDto {

    @ApiModelProperty(notes = "The database generated user id")
    private Integer id;

    @ApiModelProperty(notes = "The user's first name")
    private String firstname;

    @ApiModelProperty(notes = "The user's last name")
    private String lastname;

    @ApiModelProperty(notes = "The user's email address")
    private String email;

    @ApiModelProperty(notes = "The user's roles")
    private List<RoleDto> roles;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
