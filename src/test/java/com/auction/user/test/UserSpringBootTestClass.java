package com.auction.user.test;

import com.auction.common.constant.UserEndPointPath;
import com.auction.user.model.request.UserLoginRequest;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class UserSpringBootTestClass {
    @Autowired
    public final MockMvc mockMvc = null;

    @Autowired
    private final UserRepository userRepository = null;

    @AfterEach
    void delete_all() {
        userRepository.deleteAll();
    }

    protected ResultActions search(String accessToken, UUID userId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(UserEndPointPath.SEARCH, userId.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    protected ResultActions login(String account, String password) throws Exception {
        UserLoginRequest userLoginRequest = new UserLoginRequest(account, password);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(userLoginRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.LOGIN)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

    }

    protected ResultActions reigster_first(String account, String password, String email) throws Exception {
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
