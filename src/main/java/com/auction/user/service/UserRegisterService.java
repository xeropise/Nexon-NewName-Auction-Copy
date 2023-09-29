package com.auction.user.service;

import com.auction.user.entity.UserEntity;
import com.auction.user.exception.AccountExistsException;
import com.auction.user.model.type.RoleType;
import com.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(
            String account,
            String password,
            String email
    ) {
        boolean userExists = userRepository.existsByAccountOrEmail(account, email);

        if (!userExists) {
            password = passwordEncoder.encode(password);

            UserEntity user = UserEntity.create(account, password, email);
            user.addRole(RoleType.USER);
            userRepository.save(user);
        } else {
            throw new AccountExistsException("user already exists");
        }
    }
}
