package com.auction.user.model.request;

import com.auction.user.model.type.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserAddRoleRequest {
    @NotNull
    private RoleType roleType;
}
