package com.auction.usercharacter.repository;

import com.auction.usercharacter.entity.UserCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCharacterSearchRepository extends JpaRepository<UserCharacterEntity, UUID> {

    List<UserCharacterEntity> findAllByUserId(UUID userId);
}
