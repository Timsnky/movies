package app.safaricom.movies;

import app.safaricom.movies.model.Role;
import app.safaricom.movies.model.User;
import app.safaricom.movies.repository.RoleRepository;
import app.safaricom.movies.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testReturnRoleByName()
    {
        String email = "john@example.com";
        User user = this.saveUser(email);

        User foundUser = userRepository.findByEmail(email);

        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    public void testReturnUserExistsByEmail()
    {
        String email = "john@example.com";
        User user = this.saveUser(email);

        boolean userExists = userRepository.existsByEmail(email);

        assertThat(userExists).isTrue();
    }

    @Test
    public void testDoesntReturnUserExistsByEmail()
    {
        String email = "john@example.com";
        User user = this.saveUser(email);

        String newEmail = "john2@example.com";

        boolean userExists = userRepository.existsByEmail(newEmail);

        assertThat(userExists).isFalse();
    }

    private User saveUser(String email)
    {
        User user = new User()
                .setEmail(email)
                .setFirstname("John")
                .setLastname("Done")
                .setPassword("password");

        entityManager.persistAndFlush(user);

        return  user;
    }
}
