package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.dto.UserDto;
import com.auction.user.model.response.UserResponse;
import com.auction.user.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserSearchController {
    private final UserSearchService userSearchService;

    @GetMapping(UserEndPointPath.SEARCH)
    public ApiResponse<UserResponse> searchUser(
            @PathVariable UUID userId
    ) {
        UserDto userDto = userSearchService.findUserByUserId(userId);
        return ApiResponse.success(UserResponse.from(userDto));
    }
}
