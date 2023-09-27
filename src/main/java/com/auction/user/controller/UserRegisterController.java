package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRegisterController {
    private final UserRegisterService userRegisterService;

    @PostMapping(UserEndPointPath.REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse register(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        userRegisterService.register(
                userRegisterRequest.getAccount(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getEmail()
        );

        return ApiResponse.success(true);
    }
}
