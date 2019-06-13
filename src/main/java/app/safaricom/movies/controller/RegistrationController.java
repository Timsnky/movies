package app.safaricom.movies.controller;

import app.safaricom.movies.dto.UserDto;
import app.safaricom.movies.exception.custom.ExistingUserException;
import app.safaricom.movies.requests.SignupFormRequest;
import app.safaricom.movies.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/signup")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> signup(@RequestBody @Valid SignupFormRequest formRequest)
    {
        if (this.userService.checkExistingUser(formRequest.getEmail())) {
            throw new ExistingUserException("There is an existing user under the specified email address");
        }

        UserDto userDto = userService.getUserDtoFromUser(userService.signup(formRequest));

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
