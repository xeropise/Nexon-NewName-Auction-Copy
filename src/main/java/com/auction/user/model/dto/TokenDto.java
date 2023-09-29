package com.auction.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TokenDto {
    private UUID userId;
    private String accessToken;

    public static TokenDto from(UUID userId, String accessToken) {
        return new TokenDto(userId, accessToken);
    }
}
