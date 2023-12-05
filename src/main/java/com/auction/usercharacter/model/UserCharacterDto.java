package com.auction.usercharacter.model;

import com.auction.usercharacter.entity.UserCharacterEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCharacterDto {
    private UUID userCharacterId;
    private String name;
    private UUID userId;

    public static UserCharacterDto from(UserCharacterEntity character) {
        return new UserCharacterDto(character.getUserCharacterId(), character.getName(), character.getUserId());
    }

}
