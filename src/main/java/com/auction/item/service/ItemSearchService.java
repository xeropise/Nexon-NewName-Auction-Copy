package com.auction.item.service;

import com.auction.item.entity.ItemEntity;
import com.auction.item.model.ItemDto;
import com.auction.item.repository.ItemSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemSearchService {
    private final ItemSearchRepository itemSearchRepository;

    @Transactional(readOnly = true)
    public Page<ItemDto> searchAll(final int offset, final int size) {
        PageImpl<ItemEntity> itemPage = itemSearchRepository.findAll(PageRequest.of(offset, size));
        return itemPage.map(it -> ItemDto.from(it));
    }
}
