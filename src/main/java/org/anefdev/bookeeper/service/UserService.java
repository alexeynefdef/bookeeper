package org.anefdev.bookeeper.service;

import lombok.RequiredArgsConstructor;
import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.dto.UserDtoConverter;
import org.anefdev.bookeeper.exception.UserAlreadyExistsException;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    public List<UserDTO> getAll() {
        return UserDtoConverter.convertListOfUsersToUserDTO(repository.findAll());
    }

    public void saveUser(UserDTO userDTO) throws UserAlreadyExistsException {

        if (repository.findUserByEmail(userDTO.getEmail()) == null) {

            repository.save(UserDtoConverter.convertUserDTOtoUserEntity(userDTO,passwordEncoder));

        } else {
            throw new UserAlreadyExistsException("USER WITH EMAIL: " + userDTO.getEmail() + " ALREADY EXISTS");
        }
    }

}
