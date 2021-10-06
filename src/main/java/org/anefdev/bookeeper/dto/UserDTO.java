package org.anefdev.bookeeper.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;

}
