package app.safaricom.movies.services;

import app.safaricom.movies.dto.RoleDto;
import app.safaricom.movies.dto.UserDto;
import app.safaricom.movies.model.Role;
import app.safaricom.movies.model.User;
import app.safaricom.movies.repository.RoleRepository;
import app.safaricom.movies.repository.UserRepository;
import app.safaricom.movies.requests.SignupFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean checkExistingUser(String email) {
        return userRepository.existsByEmail(email);
    }

    public User signup(SignupFormRequest formRequest)
    {
        Role role = this.roleRepository.findByName("User");

        List<Role> userRoles = new ArrayList<>(Arrays.asList(role));

        User user = new User()
                .setFirstname(formRequest.getFirstname())
                .setLastname(formRequest.getLastname())
                .setEmail(formRequest.getEmail())
                .setPassword(passwordEncoder.encode(formRequest.getPassword()))
                .setRoles(userRoles);

        return this.userRepository.save(user);
    }

    public UserDto getUserDtoFromUser(User user)
    {
        List<RoleDto> roleDtos = new ArrayList<>();

        user.getRoles().forEach(role -> {
            roleDtos.add(role.getRoleDtoFromRole());
        });

        return new UserDto()
                .setId(user.getId())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setRoles(roleDtos);
    }
}
