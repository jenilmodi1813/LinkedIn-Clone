package com.services.controller;

import com.services.dtos.user.UpdateUserInfoRequest;
import com.services.dtos.user.UserInfoDto;
import com.services.dtos.user.UserProfileDto;
import com.services.service.UserInfoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User/UserInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("addUserInfo/{id}")
    public ResponseEntity<UserProfileDto> addUserInfo(@Valid @RequestBody UserInfoDto userInfoDto,
                                                      @PathVariable Long id){
        UserProfileDto userInfo =userInfoService.addUserInfo(userInfoDto,id);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);

    }

    @GetMapping("getById/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable Long id){
        UserProfileDto userProfileDto = userInfoService.getById(id);
        return new ResponseEntity<>(userProfileDto,HttpStatus.FOUND);
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<UserProfileDto> updateById(
            @Valid
            @PathVariable Long id,@RequestBody UpdateUserInfoRequest updateUserInfoRequest
    ){
        UserProfileDto userProfileDto = userInfoService.updateById(id,updateUserInfoRequest);
        return new ResponseEntity<>(userProfileDto,HttpStatus.FOUND);
    }

}
