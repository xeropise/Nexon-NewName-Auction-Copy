package com.auction.item.service;

import com.auction.item.entity.ItemEntity;
import com.auction.item.repository.ItemModifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemModifyService {
    private final ItemModifyRepository itemModifyRepository;

    @Transactional
    public void create(final String name, final boolean isConsumable, final String imageUrl) {
        ItemEntity item = ItemEntity.create(name, isConsumable, imageUrl);
        itemModifyRepository.save(item);
    }
}
