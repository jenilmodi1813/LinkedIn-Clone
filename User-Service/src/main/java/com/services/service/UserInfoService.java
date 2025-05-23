package com.services.service;

import com.services.dtos.user.UpdateUserInfoRequest;
import com.services.dtos.user.UserInfoDto;
import com.services.dtos.user.UserProfileDto;
import jakarta.validation.Valid;

public interface UserInfoService {
    UserProfileDto addUserInfo(@Valid UserInfoDto userInfoDto,Long id);

    UserProfileDto getById(Long id);

    UserProfileDto updateById(Long id, UpdateUserInfoRequest updateUserInfoRequest);
}
