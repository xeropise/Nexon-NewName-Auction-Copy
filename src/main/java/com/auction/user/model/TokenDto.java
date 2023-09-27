package com.auction.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    private String accessToken;

    public static TokenDto from(String accessToken) {
        return new TokenDto(accessToken);
    }
}
