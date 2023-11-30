package com.auction.character.repository;

import com.auction.character.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterSearchRepository extends JpaRepository<CharacterEntity, UUID> {


}
