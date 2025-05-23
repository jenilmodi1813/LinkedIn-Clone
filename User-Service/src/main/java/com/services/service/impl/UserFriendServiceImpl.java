package com.services.service.impl;

import com.services.dtos.friends.UserFriendDto;
import com.services.entity.User;
import com.services.exception.ApiException;
import com.services.repositories.users.UserFriendRepository;
import com.services.repositories.users.UserRepository;
import com.services.service.UserFriendService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendServiceImpl implements UserFriendService {

    @Autowired
    private UserFriendRepository userFriendRepository;

    @Autowired
    private UserRepository userRepository;

    private final String USER_DOES_NOT_EXIST = "user does not exist";
    private final String INVALID_INPUT = "INVALID INPUT";

    @Override
    public ResponseEntity<UserFriendDto> getAllFriendByuId(Long id) {

//        User user = userRepository.findById(id).orElseThrow(()->new ApiException(USER_DOES_NOT_EXIST,HttpStatus.NOT_FOUND));
//        userFriendRepository.

        //
        return null;
    }
}
