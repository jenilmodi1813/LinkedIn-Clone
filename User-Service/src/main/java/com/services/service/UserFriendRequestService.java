package com.services.service;

import com.services.dtos.user.UserFriendRequestDetailsDto;
import com.services.dtos.user.UserFriendRequestDto;
import com.services.entity.UserFriendRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;


import java.util.Set;

public interface UserFriendRequestService {
    ResponseEntity<String> getFriendRequest(@Valid Long senderId, Long reciverId);

    Set<UserFriendRequest> getAllRequestByUser(Long id);

    ResponseEntity<String> RejectRequest(Long userId, Long frirndReqId);

    ResponseEntity<String> AcceptRequest(Long userId, Long frirndReqId);
//    String getFriendRequest(Long senderId,Long reciverId);
}
