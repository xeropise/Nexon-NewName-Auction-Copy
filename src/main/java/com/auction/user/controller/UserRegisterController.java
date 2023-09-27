package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRegisterController {
    private final UserRegisterService userRegisterService;

    @PostMapping(UserEndPointPath.REGISTER)
    public ResponseEntity<Boolean> register(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        userRegisterService.register(
                userRegisterRequest.getAccount(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getEmail()
        );

        return new ResponseEntity(true, HttpStatus.CREATED);
    }
}
