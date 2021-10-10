package org.anefdev.bookeeper.service;

import lombok.RequiredArgsConstructor;
import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.exception.UserAlreadyExistsException;
import org.anefdev.bookeeper.exception.UserNotFoundException;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    public List<User> getAll() {
        return repository.findAll();
    }

    public void saveUser(UserDTO userDTO) throws UserAlreadyExistsException {

        if (repository.findUserByEmail(userDTO.getEmail()) == null) {

            var newUser = User.builder()
                    .fullname(userDTO.getFullname())
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

            repository.save(newUser);

        } else {
            throw new UserAlreadyExistsException("USER WITH EMAIL: " + userDTO.getEmail() + " ALREADY EXISTS");
        }
    }

}
