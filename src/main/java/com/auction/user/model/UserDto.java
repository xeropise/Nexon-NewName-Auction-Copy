package com.auction.user.model;

import com.auction.user.entity.UserEntity;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class UserDto {
    private UUID userId;

    private String account;

    private String password;

    private String email;

    private List<RoleType> roles;

    public UserDto(UUID userId, String account, String password, String email, List<RoleType> roles) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public boolean isAdmin() {
        return roles.stream().anyMatch(it -> RoleType.ADMIN.equals(it));
    }

    public static UserDto from(UserEntity user) {
        return new UserDto(
                user.getUserId(),
                user.getAccount(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
