package com.auction.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private String account;
    private String password;
    private String email;
}
