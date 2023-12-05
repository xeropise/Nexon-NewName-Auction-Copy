package com.auction.item.model;

import com.auction.item.entity.ItemEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDto {
    private UUID itemId;
    private String name;
    private boolean isConsumable;
    private String imageUrl;

    public static ItemDto from(ItemEntity item) {
        return new ItemDto(item.getItemId(), item.getName(), item.isConsumable(), item.getImageUrl());
    }
}
