package app.safaricom.movies.repository;

import app.safaricom.movies.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    public boolean existsByEmail(String email);
}
