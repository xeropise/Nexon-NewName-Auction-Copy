package com.auction.item.controller;

import com.auction.common.constant.ItemEndPointPath;
import com.auction.common.model.ApiResponse;
import com.auction.common.model.PageResponse;
import com.auction.item.model.ItemDto;
import com.auction.item.service.ItemSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemSearchController {
    private final ItemSearchService itemSearchService;

    @GetMapping(ItemEndPointPath.SEARCH_ITEMS)
    public ApiResponse<PageResponse> searchAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ItemDto> page = itemSearchService.searchAll(offset, size);
        return ApiResponse.success(PageResponse.from(page, offset));
    }
}
