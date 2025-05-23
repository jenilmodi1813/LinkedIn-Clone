package com.services.Auth_Service.service;

import com.services.Auth_Service.dtos.LoginUserDto;
import com.services.Auth_Service.dtos.RegisterAdminDto;
import com.services.Auth_Service.dtos.RegisterUserDto;
import com.services.Auth_Service.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto createUser(@Valid RegisterUserDto registerUserDto);

   ResponseEntity<String> login(@Valid LoginUserDto loginUserDto);

    UserDto createAdmin(@Valid RegisterAdminDto registerAdminDto);
}
