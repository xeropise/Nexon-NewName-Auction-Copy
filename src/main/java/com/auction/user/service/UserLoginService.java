package com.auction.user.service;

import com.auction.common.component.JwtProvider;
import com.auction.user.exception.PasswordNotMatchException;
import com.auction.user.model.dto.TokenDto;
import com.auction.user.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserSearchService userSearchService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public TokenDto loginByAccount(String account, String password) {
        UserDto user = userSearchService.findUserByAccount(account);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchException();
        }

        if (user.isAdmin()) {
            return TokenDto.from(user.getUserId(), jwtProvider.forAdmin(user.getUserId(), user.getRoles()));
        }

        return TokenDto.from(user.getUserId(), jwtProvider.forUser(user.getUserId(), user.getRoles()));
    }
}
