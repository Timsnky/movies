package app.safaricom.movies;

import app.safaricom.movies.model.Role;
import app.safaricom.movies.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testReturnRoleByName()
    {
        String roleName = "Test Role";
        Role role = new Role().setName(roleName);
        entityManager.persistAndFlush(role);

        Role foundRole = roleRepository.findByName(roleName);

        assertThat(foundRole.getName()).isEqualTo(roleName);
    }
}
