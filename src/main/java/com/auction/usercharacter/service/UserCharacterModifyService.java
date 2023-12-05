package com.auction.usercharacter.service;

import com.auction.usercharacter.entity.UserCharacterEntity;
import com.auction.usercharacter.repository.UserCharacterModifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCharacterModifyService {
    private final UserCharacterModifyRepository userCharacterModifyRepository;

    @Transactional
    public void create(final String name, final UUID userId) {
        UserCharacterEntity character = UserCharacterEntity.from(name, userId);
        userCharacterModifyRepository.save(character);
    }
}
