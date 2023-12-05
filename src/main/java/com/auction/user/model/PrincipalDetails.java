package com.auction.user.model;

import com.auction.user.model.type.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PrincipalDetails implements UserDetails {
    private final String userId;
    private final List<RoleType> roles;

    public PrincipalDetails(
            String userId,
            List<RoleType> roles
    ) {
        this.userId = userId;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(o -> new SimpleGrantedAuthority(o.name().toString())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    public UUID getUserId() {
        return UUID.fromString(getUsername());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
