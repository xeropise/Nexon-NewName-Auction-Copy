package com.auction.user.controller;

import com.auction.common.model.ApiResponse;
import com.auction.user.model.response.TokenResponse;
import com.auction.user.test.UserSpringBootTestClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

public class UserSearchControllerTest extends UserSpringBootTestClass {

    @Test
    @DisplayName("유저 조회")
    public void user_search_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@gmail.com";

        // 계정 등록
        register(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());

        // 로그인
        MvcResult result = login(account, password).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();

        ApiResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        String accessToken = response.getBody().getAccessToken();
        UUID userId = response.getBody().getUserId();

        // 조회
        search(accessToken, userId).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
