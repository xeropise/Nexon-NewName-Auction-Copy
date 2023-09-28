package com.auction.user.controller;

import com.auction.common.constant.UserEndPointPath;
import com.auction.user.model.request.UserLoginRequest;
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
public class UserLoginControllerTest {
    @Autowired
    private final MockMvc mockMvc = null;

    @Autowired
    private final UserRepository userRepository = null;

    @AfterEach
    void delete_all() {
        userRepository.deleteAll();
    }

    @Test
    public void user_register_login_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        reigster_first(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login(account, password).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void user_register_login_account_empty_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        reigster_first(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login("", password).andExpect(MockMvcResultMatchers.status().isBadRequest());
        login(null, password).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void user_register_login_password_empty_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        reigster_first(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login(account, null).andExpect(MockMvcResultMatchers.status().isBadRequest());
        login(account, "").andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    private ResultActions login(String account, String password) throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest(account, password);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(userLoginRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.LOGIN)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print());
    }

    private ResultActions reigster_first(String account, String password, String email) throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(account, password, email);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(userRegisterRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.REGISTER)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print());
    }
}
