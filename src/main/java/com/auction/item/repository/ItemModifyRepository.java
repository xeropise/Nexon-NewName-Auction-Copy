package com.auction.item.repository;

import com.auction.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemModifyRepository extends JpaRepository<ItemEntity, UUID> {
}
