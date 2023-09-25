package com.auction.service;

import com.auction.common.model.UserPrincipal;
import com.auction.user.entity.UserEntity;
import com.auction.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        // 일단 권한 1개
        // SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRoleType().name());
        // List<GrantedAuthority> authorities = List.of(grantedAuthority);

        // new UserPrincipal(user.getUserId().toString(), authorities);
        return null;
    }
}
