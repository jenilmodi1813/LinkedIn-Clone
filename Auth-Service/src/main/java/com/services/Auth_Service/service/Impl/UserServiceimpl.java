package com.services.Auth_Service.service.Impl;
import com.services.Auth_Service.constance.UserRoles;
import com.services.Auth_Service.dtos.LoginUserDto;
import com.services.Auth_Service.dtos.RegisterAdminDto;
import com.services.Auth_Service.dtos.RegisterUserDto;
import com.services.Auth_Service.dtos.UserDto;
import com.services.Auth_Service.entity.Login;
import com.services.Auth_Service.entity.Role;
import com.services.Auth_Service.entity.User;
import com.services.Auth_Service.exception.ApiException;
import com.services.Auth_Service.repositories.LoginRepository;
import com.services.Auth_Service.repositories.RoleRepository;
import com.services.Auth_Service.repositories.UserRepository;
import com.services.Auth_Service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceimpl implements UserService {

    private final String USER_NAME_ALREADY_EXISTS = "username already exists";
    private final String EMAIL_ALREADY_EXISTS = "email already exists";
    private final String USER_EMAIL_DOES_NOT_EXIST = "user email does not exist";
    private final String USER_PASSWORD_DOES_NOT_EXIST = "user password does not exist";
    private final String USER_ROLE_DOES_NOT_EXIST = "user role does not exist";
    private final String INVALID_CREDENTIALS = "Invalid email or password";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDto createUser(RegisterUserDto registerUserDto) {
        Optional<User> user = userRepository.findByUsername(registerUserDto.getUsername());
        if(user.isPresent()) throw new ApiException(HttpStatus.BAD_REQUEST,USER_NAME_ALREADY_EXISTS);

        Optional<User> user1 = userRepository.findByEmail(registerUserDto.getEmail());
        if(user1.isPresent()) throw new ApiException(HttpStatus.BAD_REQUEST,EMAIL_ALREADY_EXISTS);

        User userSave = new User();
        userSave.setUsername(registerUserDto.getUsername());
        userSave.setEmail(registerUserDto.getEmail());
        userSave.setPassword(registerUserDto.getPassword());
        userSave.setFirstname(registerUserDto.getFirstName());
        userSave.setLastname(registerUserDto.getLastName());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(UserRoles.USER.withPrefix());
        if(userRole == null) throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,USER_ROLE_DOES_NOT_EXIST);
        roles.add(userRole);
        userSave.setRoles(roles);

        User CreateUser = userRepository.save(userSave);

        UserDto userDto = new UserDto();
        userDto.setUsername(CreateUser.getUsername());
        userDto.setEmail(CreateUser.getEmail());
        userDto.setFirstName(CreateUser.getFirstname());
        userDto.setLastName(CreateUser.getLastname());
        return userDto;
    }

    @Override
    public ResponseEntity<String> login(LoginUserDto loginUserDto) {
        User byEmailUser = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,USER_EMAIL_DOES_NOT_EXIST));
//        User byPassword = userRepository.findByPassword(loginUserDto.getPassword()).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,USER_PASSWORD_DOES_NOT_EXIST));
        Login login = new Login();
        login.setEmail(loginUserDto.getEmail());
        login.setPassword(loginUserDto.getPassword());
        login.setUser(byEmailUser);
        loginRepository.save(login);
        return ResponseEntity.ok().body("Login SuccessFull :)");
    }

    @Override
    public UserDto createAdmin(RegisterAdminDto registerAdminDto) {
        Optional<User> user = userRepository.findByUsername(registerAdminDto.getUsername());
        if(user.isPresent()) throw new ApiException(HttpStatus.BAD_REQUEST,USER_NAME_ALREADY_EXISTS);

        Optional<User> user1 = userRepository.findByEmail(registerAdminDto.getEmail());
        if(user1.isPresent()) throw new ApiException(HttpStatus.BAD_REQUEST,EMAIL_ALREADY_EXISTS);

        User userSave = new User();
        userSave.setUsername(registerAdminDto.getUsername());
        userSave.setEmail(registerAdminDto.getEmail());
        userSave.setPassword(registerAdminDto.getPassword());
        userSave.setFirstname(registerAdminDto.getFirstName());
        userSave.setLastname(registerAdminDto.getLastName());

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(UserRoles.ADMIN.withPrefix());
//        Role userRole = roleRepository.findByName(UserRoles.USER.withPrefix());
        if(adminRole == null) throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,USER_ROLE_DOES_NOT_EXIST);
        roles.add(adminRole);
        userSave.setRoles(roles);

        User CreateUser = userRepository.save(userSave);

        UserDto userDto = new UserDto();
        userDto.setUsername(CreateUser.getUsername());
        userDto.setEmail(CreateUser.getEmail());
        userDto.setFirstName(CreateUser.getFirstname());
        userDto.setLastName(CreateUser.getLastname());
        return userDto;
    }
}
