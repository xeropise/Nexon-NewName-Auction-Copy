package com.auction.user.model.response;

import com.auction.user.model.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private UUID userId;
    private String accessToken;

    public TokenResponse() {
        this.userId = null;
        this.accessToken = null;
    }

    public static TokenResponse from(TokenDto tokenDto) {
        return new TokenResponse(tokenDto.getUserId(), tokenDto.getAccessToken());
    }
}
