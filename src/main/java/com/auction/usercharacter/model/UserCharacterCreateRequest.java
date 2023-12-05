package com.auction.usercharacter.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCharacterCreateRequest {
    @NotNull
    private String name;
}
