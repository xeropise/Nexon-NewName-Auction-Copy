package com.auction.item.repository;

import com.auction.item.entity.ItemEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface CustomItemSearchRepository {
    PageImpl<ItemEntity> findAll(Pageable pageable);
}
