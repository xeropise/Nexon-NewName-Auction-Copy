package com.auction.usercharacter.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCharacterResponse {
    private UUID userCharacterId;
    private String name;
    private UUID userId;

    public static UserCharacterResponse from(UserCharacterDto userCharacterDto) {
        return new UserCharacterResponse(userCharacterDto.getUserCharacterId(), userCharacterDto.getName(), userCharacterDto.getUserId());
    }
}
