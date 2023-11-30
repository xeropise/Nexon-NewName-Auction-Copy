package com.auction.user.controller;

import com.auction.common.model.ApiResponse;
import com.auction.user.model.response.TokenResponse;
import com.auction.user.model.type.RoleType;
import com.auction.user.test.UserSpringBootTestClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class UserRoleControllerTest extends UserSpringBootTestClass {
    @Test
    @DisplayName("유저 롤 어드민 추가")
    public void user_role_register_test() throws Exception {
        // 등록
        register(registeredUserAccount, registeredUserPassword, registeredEmail).andExpect(MockMvcResultMatchers.status().isCreated());

        // 로그인
        MvcResult result = login(registeredUserAccount, registeredUserPassword).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();

        ApiResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        String accessToken = response.getBody().getAccessToken();
        UUID userId = response.getBody().getUserId();

        // 유저 롤 등록
        addRole(accessToken, userId, RoleType.ADMIN).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("유저 롤 어드민 삭제")
    public void user_role_remove_test() throws Exception {
        // 등록
        register(registeredUserAccount, registeredUserPassword, registeredEmail).andExpect(MockMvcResultMatchers.status().isCreated());

        // 로그인
        MvcResult result = login(registeredUserAccount, registeredUserPassword).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();

        ApiResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        String accessToken = response.getBody().getAccessToken();
        UUID userId = response.getBody().getUserId();

        // 유저 롤 등록, 삭제
        addRole(accessToken, userId, RoleType.ADMIN).andExpect(MockMvcResultMatchers.status().isCreated());
        removeRole(accessToken, userId, RoleType.ADMIN).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
