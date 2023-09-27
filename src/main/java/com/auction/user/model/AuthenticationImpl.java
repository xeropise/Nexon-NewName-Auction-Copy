package com.auction.user.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationImpl implements Authentication {
    private PrincipalDetails principalDetails;
    private boolean isAuthenticated = true;

    public AuthenticationImpl(PrincipalDetails principalDetails) {
        this.principalDetails = principalDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.principalDetails.getAuthorities();
    }

    @Override
    public String getName() {
        return this.principalDetails.getUsername();
    }

    @Override
    public Object getCredentials() {
        return this.principalDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return this.principalDetails;
    }

    @Override
    public Object getPrincipal() {
        return this.principalDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
}
