package org.anefdev.bookeeper.service;

import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.model.Book;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserRepository getRepository() {
        return repository;
    }

    public User getUserById(Long id) {
        return repository.getById(id);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public String addUser(UserDTO userDTO) {
        User newUser = User.builder()
                .fullname(userDTO.getFullname())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .books(Collections.emptyList())
                .build();
        return "ADDED NEW USER";
    }

}
