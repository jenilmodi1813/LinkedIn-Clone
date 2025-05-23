package com.services.controller;

import com.services.dtos.friends.UserFriendDto;
import com.services.repositories.users.UserFriendRepository;
import com.services.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User/Friends")
public class UserFriendController {

    @Autowired
    private UserFriendService userFriendService;
    //get all friend
    //remove friend
    @GetMapping("getAllByUserId/{id}")
    public ResponseEntity<UserFriendDto> getAllFriendByuId(@PathVariable Long id){
        return userFriendService.getAllFriendByuId(id);
    }

}
