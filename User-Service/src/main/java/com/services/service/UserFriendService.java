package com.services.service;

import com.services.dtos.friends.UserFriendDto;
import org.springframework.http.ResponseEntity;

public interface UserFriendService {
    ResponseEntity<UserFriendDto> getAllFriendByuId(Long id);
}
