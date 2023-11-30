package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.user.model.request.UserAddRoleRequest;
import com.auction.user.model.request.UserRemoveRoleRequest;
import com.auction.user.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping(UserEndPointPath.ADD_ROLE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse addRole(
            @PathVariable UUID userId,
            @Valid @RequestBody UserAddRoleRequest userAddRoleRequest
    ) {
        userRoleService.addRole(userId, userAddRoleRequest.getRoleType());
        return ApiResponse.created(null);
    }

    @DeleteMapping(UserEndPointPath.REMOVE_ROLE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse removeRole(
            @PathVariable UUID userId,
            @Valid @RequestBody UserRemoveRoleRequest userRemoveRoleRequest
    ) {
        userRoleService.removeRole(userId, userRemoveRoleRequest.getRoleType());
        return ApiResponse.noContent(null);
    }
}
