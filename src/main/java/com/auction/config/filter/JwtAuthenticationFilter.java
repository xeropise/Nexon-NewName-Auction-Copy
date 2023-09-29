package com.auction.config.filter;

import com.auction.common.component.JwtVerifier;
import com.auction.user.model.AuthenticationImpl;
import com.auction.user.model.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtVerifier jwtVerifier;

    private final String TOKEN_PREFIX = "Bearer ";

    public String findTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authorizationHeader)) {
            authorizationHeader = "";
        } else if (!authorizationHeader.startsWith(TOKEN_PREFIX)) {
            authorizationHeader = "";
        }
        return authorizationHeader = authorizationHeader.replace(TOKEN_PREFIX, "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = findTokenFromHeader(request);
        authenticate(jwtToken);
        filterChain.doFilter(request, response);
    }

    private void authenticate(String jwtToken) {
        boolean notAuthenticate = jwtToken.isEmpty() || !jwtVerifier.verify(jwtToken);

        if (notAuthenticate) {
            return;
        }

        PrincipalDetails principalDetails = new PrincipalDetails(
                jwtVerifier.getAccount(jwtToken),
                jwtVerifier.getRoles(jwtToken)
        );

        SecurityContextHolder.getContext().setAuthentication(new AuthenticationImpl(principalDetails));
    }
}
