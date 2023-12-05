package com.auction.usercharacter.repository;

import com.auction.usercharacter.entity.UserCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCharacterModifyRepository extends JpaRepository<UserCharacterEntity, UUID> {
}
