package com.auction.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserLoginRequest {
    @NotEmpty(message = "user account must not be null or empty")
    private String account;

    @NotEmpty(message = "user password must not be null or empty")
    private String password;
}
