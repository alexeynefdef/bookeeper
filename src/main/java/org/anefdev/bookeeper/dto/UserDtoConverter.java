package org.anefdev.bookeeper.dto;

import org.anefdev.bookeeper.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserDtoConverter {

    public static User convertUserDTOtoUserEntity(UserDTO userDTO, PasswordEncoder passwordEncoder) {

        return User.builder()
                .id(userDTO.getId())
                .fullname(userDTO.getFullname())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();

    }

    public static List<UserDTO> convertListOfUsersToUserDTO(List<User> users) {

        if (!users.isEmpty()) {

            return users.stream().map(user -> UserDTO.builder()
                    .id(user.getId())
                    .fullname(user.getFullname())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .build()
            ).collect(Collectors.toList());

        } else return Collections.emptyList();

    }
}
