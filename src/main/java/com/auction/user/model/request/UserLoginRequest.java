package com.auction.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    private String account;
    private String password;
}
