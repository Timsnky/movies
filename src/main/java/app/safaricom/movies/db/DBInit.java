package app.safaricom.movies.db;

import app.safaricom.movies.model.Rating;
import app.safaricom.movies.model.Role;
import app.safaricom.movies.repository.RatingRepository;
import app.safaricom.movies.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {

    private RoleRepository roleRepository;

    private RatingRepository ratingRepository;

    public DBInit(RoleRepository roleRepository, RatingRepository ratingRepository) {
        this.roleRepository = roleRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        this.addRoles();
        this.addRatings();
    }

    private void addRoles()
    {
        this.roleRepository.deleteAll();

        this.roleRepository.saveAll(
                Arrays.asList(
                        new Role().setName("Admin"),
                        new Role().setName("Manager"),
                        new Role().setName("User")
                )
        );
    }

    private void addRatings()
    {
        this.ratingRepository.deleteAll();

        this.ratingRepository.saveAll(
                Arrays.asList(
                    new Rating().setRating(1),
                    new Rating().setRating(2),
                    new Rating().setRating(3),
                    new Rating().setRating(4),
                    new Rating().setRating(5)
                )
        );
    }
}
