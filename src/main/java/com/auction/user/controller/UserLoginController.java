package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.dto.TokenDto;
import com.auction.user.model.request.UserLoginRequest;
import com.auction.user.model.response.TokenResponse;
import com.auction.user.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping(UserEndPointPath.LOGIN)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TokenResponse> login(
            @Valid @RequestBody UserLoginRequest userLoginRequest
    ) {
        TokenDto tokenDto = userLoginService.loginByAccount(userLoginRequest.getAccount(), userLoginRequest.getPassword());
        return ApiResponse.success(TokenResponse.from(tokenDto));
    }
}
