package com.auction.item.repository;

import com.auction.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemSearchRepository extends JpaRepository<ItemEntity, UUID>, CustomItemSearchRepository {
}
