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
import org.springframework.test.web.servlet.ResultActions;
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
        user_register_mockmvc("xeropise", "1234", "whrbql1@naver.com")
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void user_register_request_account_null_test() throws Exception {
        user_register_mockmvc(null, "1234", "whrbql1@gmail.com")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        user_register_mockmvc("", "1234", "whrbql1@gmail.com")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void user_register_request_password_null_test() throws Exception {
        user_register_mockmvc("xeropise", null, "whrbql1@gmail.com")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        user_register_mockmvc("xeropise", "", "whrbql1@gmail.com")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void user_register_request_email_null_test() throws Exception {
        user_register_mockmvc("xeropise", "1234", null)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        user_register_mockmvc("xeropise", "1234", "")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        user_register_mockmvc("xeropise", "1234", "testtest")
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private ResultActions user_register_mockmvc(
            String account,
            String password,
            String email
    ) throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(account, password, email);

        String content = new ObjectMapper().writeValueAsString(userRegisterRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.REGISTER)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print());
    }
}
