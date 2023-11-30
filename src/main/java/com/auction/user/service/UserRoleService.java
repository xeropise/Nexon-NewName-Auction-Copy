package com.auction.user.service;

import com.auction.user.entity.RoleEntity;
import com.auction.user.entity.UserEntity;
import com.auction.user.exception.RoleNotFoundException;
import com.auction.user.exception.UserNotFoundException;
import com.auction.user.model.type.RoleType;
import com.auction.user.repository.RoleRepository;
import com.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void addRole(final UUID userId, RoleType roleType) {
        UserEntity user = userRepository.findWithFetchByUserId(userId).orElseThrow(UserNotFoundException::new);
        RoleEntity role = roleRepository.findByRoleType(roleType).orElseThrow(RoleNotFoundException::new);
        user.addRole(role);
    }

    @Transactional
    public void removeRole(final UUID userId, RoleType roleType) {
        UserEntity user = userRepository.findWithFetchByUserId(userId).orElseThrow(UserNotFoundException::new);
        RoleEntity role = roleRepository.findByRoleType(roleType).orElseThrow(RoleNotFoundException::new);
        user.removeRole(role);
    }
}
