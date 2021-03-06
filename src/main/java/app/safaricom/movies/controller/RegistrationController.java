package app.safaricom.movies.controller;

import app.safaricom.movies.dto.UserDto;
import app.safaricom.movies.events.UserRegistered;
import app.safaricom.movies.exception.custom.ExistingUserException;
import app.safaricom.movies.model.User;
import app.safaricom.movies.requests.SignupFormRequest;
import app.safaricom.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/signup")
public class RegistrationController {

    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> signup(@RequestBody @Valid SignupFormRequest formRequest)
    {
        if (this.userService.checkExistingUser(formRequest.getEmail())) {
            throw new ExistingUserException("There is an existing user under the specified email address");
        }

        User user = userService.signup(formRequest);

        UserDto userDto = userService.getUserDtoFromUser(user);

        publisher.publishEvent(new UserRegistered(this, user));

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
