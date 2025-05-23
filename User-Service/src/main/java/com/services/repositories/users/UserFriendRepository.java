package com.services.repositories.users;

import com.services.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend,Long> {
}
