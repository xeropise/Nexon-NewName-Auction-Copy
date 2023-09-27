package com.auction.user.service;

import com.auction.common.component.JwtProvider;
import com.auction.user.exception.PasswordNotMatchException;
import com.auction.user.model.RoleType;
import com.auction.user.model.TokenDto;
import com.auction.user.model.UserDto;
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
            return TokenDto.from(jwtProvider.forAdmin(user.getAccount(), user.getRoles()));
        }

        return TokenDto.from(jwtProvider.forUser(user.getAccount(), user.getRoles()));
    }
}
