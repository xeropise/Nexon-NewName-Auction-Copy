package com.auction.usercharacter.controller;

import com.auction.common.constant.CharacterEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.PrincipalDetails;
import com.auction.usercharacter.model.UserCharacterCreateRequest;
import com.auction.usercharacter.service.UserCharacterModifyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCharacterCreateController {
    private final UserCharacterModifyService userCharacterModifyService;

    @PostMapping(CharacterEndPointPath.CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> create(
            @Valid @RequestBody UserCharacterCreateRequest userCharacterCreateRequest,
            @AuthenticationPrincipal PrincipalDetails signedUser
    ) {
        userCharacterModifyService.create(userCharacterCreateRequest.getName(), signedUser.getUserId());
        return ApiResponse.created(null);
    }
}
