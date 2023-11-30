package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.request.UserAddRoleRequest;
import com.auction.user.model.request.UserRemoveRoleRequest;
import com.auction.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping(UserEndPointPath.ADD_ROLE)
    public ApiResponse addRole(
            @PathVariable UUID userId,
            @RequestBody UserAddRoleRequest userAddRoleRequest
    ) {
        userRoleService.addRole(userId, userAddRoleRequest.getRoleType());
        return ApiResponse.success(null);
    }

    @DeleteMapping(UserEndPointPath.REMOVE_ROLE)
    public ApiResponse removeRole(
            @PathVariable UUID userId,
            @RequestBody UserRemoveRoleRequest userRemoveRoleRequest
    ) {
        userRoleService.removeRole(userId, userRemoveRoleRequest.getRoleType());
        return ApiResponse.success(null);
    }
}
