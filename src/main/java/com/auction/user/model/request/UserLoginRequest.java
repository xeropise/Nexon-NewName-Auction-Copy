package com.auction.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    @NotEmpty(message = "user account must not be null or empty")
    private String account;

    @NotEmpty(message = "user password must not be null or empty")
    private String password;
}
