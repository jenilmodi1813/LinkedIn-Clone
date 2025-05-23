package com.services.service.impl;

import com.services.dtos.user.UpdateUserInfoRequest;
import com.services.dtos.user.UserInfoDto;
import com.services.dtos.user.UserProfileDto;
import com.services.entity.User;
import com.services.entity.UserInfo;
import com.services.exception.ApiException;
import com.services.repositories.users.UserInfoRepository;
import com.services.repositories.users.UserRepository;
import com.services.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final String USER_DOES_NOT_EXIST = "user does not exist";
    private final String USER_ID_NOT_EXIST = "user id not exist";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserProfileDto addUserInfo(UserInfoDto userInfoDto,Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new ApiException(USER_DOES_NOT_EXIST,HttpStatus.NOT_FOUND));

        UserInfo saveInfo = new UserInfo();
        saveInfo.setUser(user);
        saveInfo.setAddress(userInfoDto.getAddress());
        saveInfo.setZipcode(userInfoDto.getZipCode());
        saveInfo.setCity(userInfoDto.getCity());
        saveInfo.setCountry(userInfoDto.getCountry());
        saveInfo.setWebsite(userInfoDto.getWebsite());
        saveInfo.setProfessionalSummary(userInfoDto.getProfessionalSummary());
        saveInfo.setHeadline(userInfoDto.getHeadLine());
        saveInfo.setDob(userInfoDto.getDob());

        UserInfo userInfo = userInfoRepository.save(saveInfo);

        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setAddress(userInfo.getAddress());
        userProfileDto.setZipCode(userInfo.getZipcode());
        userProfileDto.setCity(userInfo.getCity());
        userProfileDto.setCountry(userInfo.getCountry());
        userProfileDto.setWebsite(userInfo.getWebsite());
        userProfileDto.setProfessionalSummary(userInfo.getProfessionalSummary());
        userProfileDto.setHeadLine(userInfo.getHeadline());
        userProfileDto.setDob(userInfo.getDob());
        return userProfileDto;
    }

    @Override
    public UserProfileDto getById(Long id) {
        if(id == null) throw new ApiException(USER_ID_NOT_EXIST,HttpStatus.BAD_REQUEST);

        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new ApiException(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND));
        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setAddress(userInfo.getAddress());
        userProfileDto.setZipCode(userInfo.getZipcode());
        userProfileDto.setCity(userInfo.getCity());
        userProfileDto.setCountry(userInfo.getCountry());
        userProfileDto.setWebsite(userInfo.getWebsite());
        userProfileDto.setProfessionalSummary(userInfo.getProfessionalSummary());
        userProfileDto.setHeadLine(userInfo.getHeadline());
        userProfileDto.setDob(userInfo.getDob());
        return userProfileDto;
    }

    @Override
    public UserProfileDto updateById(Long id, UpdateUserInfoRequest updateUserInfoRequest) {
        if(id == null) throw new ApiException(USER_ID_NOT_EXIST,HttpStatus.BAD_REQUEST);
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new ApiException(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND));
        userInfo.setAddress(updateUserInfoRequest.getAddress());
        userInfo.setZipcode(updateUserInfoRequest.getZipCode());
        userInfo.setCity(updateUserInfoRequest.getCity());
        userInfo.setCountry(updateUserInfoRequest.getCountry());
        userInfo.setWebsite(updateUserInfoRequest.getWebsite());
        userInfo.setProfessionalSummary(updateUserInfoRequest.getProfessionalSummary());
        userInfo.setHeadline(updateUserInfoRequest.getHeadLine());
        userInfo.setDob(updateUserInfoRequest.getDob());

        UserInfo updateInfo = userInfoRepository.save(userInfo);

        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setAddress(userInfo.getAddress());
        userProfileDto.setZipCode(userInfo.getZipcode());
        userProfileDto.setCity(userInfo.getCity());
        userProfileDto.setCountry(userInfo.getCountry());
        userProfileDto.setWebsite(userInfo.getWebsite());
        userProfileDto.setProfessionalSummary(userInfo.getProfessionalSummary());
        userProfileDto.setHeadLine(userInfo.getHeadline());
        userProfileDto.setDob(userInfo.getDob());
        return userProfileDto;
    }


}
