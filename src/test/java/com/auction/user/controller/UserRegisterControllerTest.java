package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRegisterControllerTest {
    @Autowired
    private final MockMvc mockMvc = null;

    @Autowired
    private final UserRepository userRepository = null;

    @AfterEach
    void delete_all() {
        userRepository.deleteAll();
    }

    @Test
    public void user_register_test() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("xeropise", "1234", "whrbql1@naver.com");

        String content = new ObjectMapper().writeValueAsString(userRegisterRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.REGISTER)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
