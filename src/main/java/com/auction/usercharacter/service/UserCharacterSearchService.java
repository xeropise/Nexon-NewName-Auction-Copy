package com.auction.usercharacter.service;

import com.auction.usercharacter.model.UserCharacterDto;
import com.auction.usercharacter.repository.UserCharacterSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCharacterSearchService {
    private final UserCharacterSearchRepository userCharacterSearchRepository;

    @Transactional(readOnly = true)
    public List<UserCharacterDto> searchList(final UUID userId) {
        return userCharacterSearchRepository.findAllByUserId(userId).stream().map(UserCharacterDto::from).toList();
    }
}
