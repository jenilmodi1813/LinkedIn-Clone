package com.services.controller;

import com.services.dtos.user.UserFriendRequestDetailsDto;
import com.services.dtos.user.UserFriendRequestDto;
import com.services.entity.UserFriendRequest;
import com.services.service.UserFriendRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("User/FriendRequest")
public class UserFriendRequestController {

    @Autowired
    private UserFriendRequestService userFriendRequestService;

    @GetMapping("getFriendRequest/{senderId}/{reciverId}")
    public ResponseEntity<String> getFriendRequest(@Valid @PathVariable Long senderId, @PathVariable Long reciverId){
        return userFriendRequestService.getFriendRequest(senderId,reciverId);
    }

    @GetMapping("getAllRequestByUser/{id}")
    public Set<UserFriendRequest> getAllRequestByUser(@PathVariable Long id){
        return userFriendRequestService.getAllRequestByUser(id);
    }

    @DeleteMapping("RejectReqByFriendId/{userId}/{frirndReqId}")
    public ResponseEntity<String> RejectRequest(@PathVariable Long userId,@PathVariable Long frirndReqId){
        return userFriendRequestService.RejectRequest(userId,frirndReqId);
    }

    @GetMapping("AcceptReqByFriendId/{userId}/{frirndReqId}")
    public ResponseEntity<String> AcceptRequest(@PathVariable Long userId,@PathVariable Long frirndReqId){
        return userFriendRequestService.AcceptRequest(userId,frirndReqId);
    }
}
