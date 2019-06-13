package app.safaricom.movies.security.user;

import app.safaricom.movies.model.User;
import app.safaricom.movies.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid user email/password combination");
        }

        return new UserPrincipal(user);
    }
}
