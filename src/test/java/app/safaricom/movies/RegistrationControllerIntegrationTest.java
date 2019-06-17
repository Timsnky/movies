package app.safaricom.movies;

import app.safaricom.movies.controller.RegistrationController;
import app.safaricom.movies.requests.SignupFormRequest;
import app.safaricom.movies.security.user.UserPrincipalDetailsService;
import app.safaricom.movies.services.UserService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void testReturnsUserExistingForDuplicateUser() throws Exception
    {
        SignupFormRequest signupFormRequest = this.getSignUpRequest();

        when(userService.checkExistingUser(signupFormRequest.getEmail()))
                .thenReturn(true);

        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
        );
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
}
