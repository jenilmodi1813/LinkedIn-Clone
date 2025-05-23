package com.services.Auth_Service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
//    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
