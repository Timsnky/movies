package app.safaricom.movies.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@ApiModel(description = "A data transfer object to expose the Role entity details")
public class RoleDto {

    @ApiModelProperty(notes = "The database generated role id")
    private Integer id;

    @ApiModelProperty(notes = "The role's name")
    private String name;
}
