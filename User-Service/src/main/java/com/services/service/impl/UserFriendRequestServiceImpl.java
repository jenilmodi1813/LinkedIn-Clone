package com.services.service.impl;

import com.services.dtos.user.UserFriendRequestDetailsDto;
import com.services.entity.User;
import com.services.entity.UserFriend;
import com.services.entity.UserFriendRequest;
import com.services.exception.ApiException;
import com.services.repositories.LoginRepository;
import com.services.repositories.users.UserFriendRepository;
import com.services.repositories.users.UserFriendRequestRepository;
import com.services.repositories.users.UserRepository;
import com.services.service.UserFriendRequestService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendRequestServiceImpl implements UserFriendRequestService {

    private final String USER_DOES_NOT_EXIST = "user does not exist";
    private final String INVALID_INPUT = "INVALID INPUT";
//    private final String EXPERIENCE_DOES_NOT_EXIST = "experience not exist";
    private final String FRIEND_REQUEST_ALREADY_EXIST = "Friend Request exist";
    private final String FRIEND_REQUEST_NOT_EXIST = "Friend Request Not exist";

    @Autowired
    private UserFriendRequestRepository userFriendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFriendRepository userFriendRepository;

    @Override
    public ResponseEntity<String> getFriendRequest(Long senderId, Long reciverId) {
        if(senderId == reciverId){throw new ApiException(INVALID_INPUT,HttpStatus.NOT_ACCEPTABLE);}
        User senderUser = userRepository.findById(senderId).orElseThrow(()->new ApiException(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND));
        User reciverUser = userRepository.findById(reciverId).orElseThrow(()->new ApiException(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND));
        Optional<UserFriendRequest> existFriendReq = userFriendRequestRepository.findBySenderId(senderId);
        if(existFriendReq.isPresent()){throw new ApiException(FRIEND_REQUEST_ALREADY_EXIST,HttpStatus.FOUND);}

        UserFriendRequest userFriendRequest = new UserFriendRequest();
        userFriendRequest.setSender(senderUser);
        userFriendRequest.setReceiver(reciverUser);
        userFriendRequest.setStatus("Pending");
        reciverUser.getReceivedRequests().add(userFriendRequest);
        userFriendRequestRepository.save(userFriendRequest);

//        UserFriendRequestDto userFriendRequestDto = new UserFriendRequestDto();
//        userFriendRequestDto.setUsername(userFriendRequestiend.getUsername());
//        userFriendRequestDto.setFirstName(userFriendRequestiend.getFirstname());
//        userFriendRequestDto.setLastName(userFriendRequestiend.getLastname());
        return ResponseEntity.ok().body("Request Sent :) ");
    }

    @Override
    public Set<UserFriendRequest> getAllRequestByUser(Long id) {
        if(id == null){throw new ApiException(INVALID_INPUT,HttpStatus.NOT_ACCEPTABLE);}
        User user = userRepository.findById(id).orElse(null);

        Set<UserFriendRequest> byReciverId = user.getReceivedRequests();


        return byReciverId;
    }

    @Override
    public ResponseEntity<String> RejectRequest(Long userId, Long frirndReqId) {
        UserFriendRequest byId = userFriendRequestRepository.findById(frirndReqId).orElseThrow(()->new ApiException(FRIEND_REQUEST_NOT_EXIST,HttpStatus.NOT_FOUND));
        userFriendRequestRepository.deleteByReceiverIdAndSenderId(userId,frirndReqId);
        return ResponseEntity.ok().body("Request Rejected :( ");
    }

    @Override
    public ResponseEntity<String> AcceptRequest(Long userId, Long frirndReqId) {
        UserFriendRequest byId = userFriendRequestRepository.findById(frirndReqId).orElseThrow(()->new ApiException(FRIEND_REQUEST_NOT_EXIST,HttpStatus.NOT_FOUND));
        User friend = userRepository.findById(frirndReqId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        UserFriend userFriend = new UserFriend();
        userFriend.setUsername(friend.getUsername());
        userFriend.setFirstName(friend.getFirstname());
        userFriend.setLastName(friend.getLastname());
        userFriend.setFriendId(friend.getId());

        Set<User> userFriends = user.getFriends();
        if (userFriends == null) {
            userFriends = new HashSet<>();
        }
        userFriends.add(friend);
        user.setFriends(userFriends);
        userRepository.save(user);
        userFriendRepository.save(userFriend);
        userFriendRequestRepository.deleteById(frirndReqId);
        return ResponseEntity.ok().body("Friend Add:( ");
    }


}
