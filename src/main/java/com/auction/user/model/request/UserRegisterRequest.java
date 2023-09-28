package com.auction.user.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequest {
    @NotEmpty(message = "user account must not be null or empty")
    private String account;

    @NotEmpty(message = "user password must not be null or empty")
    private String password;

    @Email
    @NotEmpty(message = "user email must not be null or empty")
    private String email;
}
