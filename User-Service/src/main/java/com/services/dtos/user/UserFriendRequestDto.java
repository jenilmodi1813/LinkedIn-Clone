package com.services.dtos.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendRequestDto {

    @NotEmpty
    private Long senderId;
    @NotEmpty
    private Long reciverId;
}
