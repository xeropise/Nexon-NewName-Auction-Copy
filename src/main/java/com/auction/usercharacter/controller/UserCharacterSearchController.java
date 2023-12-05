package com.auction.usercharacter.controller;

import com.auction.common.constant.CharacterEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.usercharacter.model.UserCharacterDto;
import com.auction.usercharacter.model.UserCharacterResponse;
import com.auction.usercharacter.service.UserCharacterSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserCharacterSearchController {
    private final UserCharacterSearchService userCharacterSearchService;

    @GetMapping(CharacterEndPointPath.SEARCH_LIST)
    public ApiResponse<List<UserCharacterResponse>> searchCharacters(
            @PathVariable UUID userId
    ) {
        List<UserCharacterDto> userCharacterDtos = userCharacterSearchService.searchList(userId);
        return ApiResponse.success(userCharacterDtos.stream().map(UserCharacterResponse::from).toList());
    }
}
