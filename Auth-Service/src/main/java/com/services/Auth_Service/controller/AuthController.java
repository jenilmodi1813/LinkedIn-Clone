package com.services.Auth_Service.controller;

import com.services.Auth_Service.dtos.LoginUserDto;
import com.services.Auth_Service.dtos.RegisterAdminDto;
import com.services.Auth_Service.dtos.RegisterUserDto;
import com.services.Auth_Service.dtos.UserDto;
import com.services.Auth_Service.repositories.UserRepository;
import com.services.Auth_Service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RegisterUserDto registerUserDto){

        UserDto userDto = userService.createUser(registerUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto loginUserDto) {
        return userService.login(loginUserDto);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<UserDto> createAdmin(@Valid @RequestBody RegisterAdminDto registerAdminDto){

        UserDto userDto = userService.createAdmin(registerAdminDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

}
