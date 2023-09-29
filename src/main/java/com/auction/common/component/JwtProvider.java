package com.auction.common.component;

import com.auction.user.model.type.RoleType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.auction.common.constant.JwtConstant.ROLES;

@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String secret;

    public String forUser(UUID userId, List<RoleType> roles) {
        return JWT.create()
                .withSubject(userId.toString())
                .withClaim(ROLES, roles.stream().map(it -> it.name()).toList())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(30).toInstant())).sign(Algorithm.HMAC256(secret));
    }

    public String forAdmin(UUID userId, List<RoleType> roles) {
        return JWT.create()
                .withSubject(userId.toString())
                .withClaim(ROLES, roles.stream().map(it -> it.name()).toList())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(30).toInstant())).sign(Algorithm.HMAC256(secret));
    }
}
