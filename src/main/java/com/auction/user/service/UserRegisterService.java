package com.auction.user.service;

import com.auction.user.entity.RoleEntity;
import com.auction.user.entity.UserEntity;
import com.auction.user.exception.RoleNotFoundException;
import com.auction.user.exception.UserAlreadyExistException;
import com.auction.user.model.type.RoleType;
import com.auction.user.repository.RoleRepository;
import com.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(
            String account,
            String password,
            String email
    ) {
        boolean userExists = userRepository.existsByAccountOrEmail(account, email);

        if (!userExists) {
            password = passwordEncoder.encode(password);

            UserEntity user = UserEntity.create(account, password, email);

            RoleEntity userRole = roleRepository.findByRoleType(RoleType.USER).orElseThrow(RoleNotFoundException::new);
            user.addRole(userRole);
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException();
        }
    }
}
