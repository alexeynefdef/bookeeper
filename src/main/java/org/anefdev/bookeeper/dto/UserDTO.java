package org.anefdev.bookeeper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;

}
