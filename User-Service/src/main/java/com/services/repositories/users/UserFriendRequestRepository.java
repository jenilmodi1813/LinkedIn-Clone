package com.services.repositories.users;

import com.services.entity.UserFriendRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserFriendRequestRepository extends JpaRepository<UserFriendRequest,Long> {

    Optional<UserFriendRequest> findBySenderId(Long id);

    Set<UserFriendRequest> findByReceiverId(Long id);

    @Modifying
    @Transactional
    void deleteByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
