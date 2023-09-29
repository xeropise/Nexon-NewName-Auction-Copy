package com.auction.user.service;

import com.auction.user.entity.UserEntity;
import com.auction.user.exception.UserNotFoundException;
import com.auction.user.model.dto.UserDto;
import com.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSearchService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto findUserByUserId(UUID userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found by userId"));

        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public UserDto findUserByAccount(String account) {
        UserEntity user = userRepository.findByAccount(account)
                .orElseThrow(() -> new UserNotFoundException("user not found by account"));

        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found by email"));

        return UserDto.from(user);
    }
}
