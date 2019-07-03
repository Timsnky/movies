package app.safaricom.movies;

import app.safaricom.movies.controller.RegistrationController;
import app.safaricom.movies.dto.UserDto;
import app.safaricom.movies.model.User;
import app.safaricom.movies.requests.SignupFormRequest;
import app.safaricom.movies.security.user.UserPrincipalDetailsService;
import app.safaricom.movies.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class RegistrationControllerIntegrationTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidatesUserInputData() throws Exception {
        SignupFormRequest signupFormRequest = this.getSignUpRequest();
        signupFormRequest.setFirstname("");
        signupFormRequest.setPassword_confirmation("diff_password");

        String[] expectedErrors = {"Firstname should not be blank", "Passwords entered do not match"};

        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(signupFormRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Validation error"))
                .andExpect(jsonPath("$.subErrors[*].message", containsInAnyOrder(expectedErrors)));
    }

    @Test
    public void testReturnsUserExistingForDuplicateUser() throws Exception
    {
        SignupFormRequest signupFormRequest = this.getSignUpRequest();

        when(userService.checkExistingUser(signupFormRequest.getEmail()))
                .thenReturn(true);

        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(signupFormRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("There is an existing user under the specified email address"));
    }

    @Test
    public void testReturnsCreatedUserDto() throws Exception
    {
        SignupFormRequest signupFormRequest = this.getSignUpRequest();

        UserDto expectedUserDto = this.getUserDto(signupFormRequest);

        User expectedUser = this.getUser(signupFormRequest);

        when(userService.checkExistingUser(signupFormRequest.getEmail())).thenReturn(false);

        when(userService.signup(signupFormRequest)).thenReturn(expectedUser);

        when(userService.getUserDtoFromUser(expectedUser)).thenReturn(expectedUserDto);

        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(signupFormRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value(expectedUserDto.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(expectedUserDto.getLastname()))
                .andExpect(jsonPath("$.email").value(expectedUserDto.getEmail()));
    }

    private SignupFormRequest getSignUpRequest()
    {
        return new SignupFormRequest()
                .setEmail("john@example.com")
                .setFirstname("John")
                .setLastname("Doe")
                .setPassword("password")
                .setPassword_confirmation("password");
    }

    private User getUser(SignupFormRequest signupFormRequest)
    {
        return new User()
                .setEmail(signupFormRequest.getEmail())
                .setFirstname(signupFormRequest.getFirstname())
                .setLastname(signupFormRequest.getLastname());
    }

    private UserDto getUserDto(SignupFormRequest signupFormRequest)
    {
        return new UserDto()
                .setEmail(signupFormRequest.getEmail())
                .setFirstname(signupFormRequest.getFirstname())
                .setLastname(signupFormRequest.getLastname());
    }
}
