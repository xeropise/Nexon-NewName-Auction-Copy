package com.auction.user.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    String userId;
    String password;
}
