package com.auction.common.component;

import com.auction.user.model.type.RoleType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.auction.common.constant.JwtConstant.ROLES;

@Component
public class JwtVerifier {
    private String secret;
    private JWTVerifier jwtVerifier;

    /**
     * @param secret
     * @see com.auction.config.filter.JwtAuthenticationFilter
     * jwtAuthenticationFilter 에 빈으로 등록 중이여서 생성자 주입하지 않으면
     * value annotation null
     */
    public JwtVerifier(@Value("${jwt.secret.key}") String secret) {
        this.secret = secret;
        this.jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
    }

    public boolean verify(String accessToken) {
        try {
            jwtVerifier.verify(accessToken);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getAccount(String accessToken) {
        String account = jwtVerifier.verify(accessToken).getSubject();

        if (Objects.isNull(account)) {
            throw new InsufficientAuthenticationException("request not authenticated");
        }
        return account;
    }

    public List<RoleType> getRoles(String accessToken) {
        List<String> roles = jwtVerifier.verify(accessToken).getClaim(ROLES).asList(String.class);

        if (Objects.isNull(roles) || roles.isEmpty()) {
            throw new InsufficientAuthenticationException("request not authenticated");
        }

        return roles.stream().map(it -> RoleType.valueOf(it)).toList();
    }
}
