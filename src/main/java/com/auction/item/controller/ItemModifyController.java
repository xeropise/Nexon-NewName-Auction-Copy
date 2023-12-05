package com.auction.item.controller;

import com.auction.common.constant.ItemEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.item.model.ItemCreateRequest;
import com.auction.item.service.ItemModifyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemModifyController {
    private final ItemModifyService itemModifyService;

    @PostMapping(ItemEndPointPath.CREATE_ITEMS)
    public ApiResponse<Void> create(
            @RequestBody @Valid ItemCreateRequest itemCreateRequest
    ) {
        itemModifyService.create(itemCreateRequest.getName(), itemCreateRequest.isConsumable(), itemCreateRequest.getImageUrl());
        return ApiResponse.created(null);
    }
}
