package org.anefdev.bookeeper.controller;

import lombok.Getter;
import org.anefdev.bookeeper.dto.UserDTO;
import org.anefdev.bookeeper.exception.UserAlreadyExistsException;
import org.anefdev.bookeeper.exception.UserNotFoundException;
import org.anefdev.bookeeper.model.User;
import org.anefdev.bookeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@RequestMapping(path = "bookeeper/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("all")
    public List<User> findAllUsers() {
        return userService.getAll();
    }

    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {

        try {
            userService.saveUser(user);

            return new ResponseEntity<>(
                    "ADDED NEW USER: " + user.getUsername(),
                    HttpStatus.CREATED
            );
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
