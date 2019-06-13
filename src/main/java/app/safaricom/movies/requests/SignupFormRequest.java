package app.safaricom.movies.requests;

import app.safaricom.movies.validation.PasswordConfirmed;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@PasswordConfirmed
public class SignupFormRequest {

    @NotBlank(message = "Firstname should not be blank")
    private String firstname;

    @NotBlank(message = "Lastname should not be blank")
    private String lastname;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email provided is invalid")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password;

    @NotBlank(message = "Password confirmation should not be blank")
    @Size(min = 8, message = "Password should have a minimum of 8 characters")
    private String password_confirmation;
}
