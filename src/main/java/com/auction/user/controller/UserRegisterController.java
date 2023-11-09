package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.service.UserRegisterService;
import jakarta.validation.Valid;
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
    public ApiResponse registerUser(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        userRegisterService.registerUser(
                userRegisterRequest.getAccount(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getEmail()
        );

        return ApiResponse.success(true);
    }
}
