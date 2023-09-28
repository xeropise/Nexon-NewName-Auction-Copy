package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.TokenDto;
import com.auction.user.model.request.UserLoginRequest;
import com.auction.user.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping(UserEndPointPath.LOGIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TokenDto> login(
            @Valid @RequestBody UserLoginRequest userLoginRequest
    ) {
        TokenDto token = userLoginService.loginByAccount(userLoginRequest.getAccount(), userLoginRequest.getPassword());
        return ApiResponse.success(token);
    }
}
