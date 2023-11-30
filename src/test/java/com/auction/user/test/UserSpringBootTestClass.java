package com.auction.user.test;

import com.auction.common.constant.UserEndPointPath;
import com.auction.user.model.request.UserAddRoleRequest;
import com.auction.user.model.request.UserLoginRequest;
import com.auction.user.model.request.UserRegisterRequest;
import com.auction.user.model.type.RoleType;
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

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected final UUID registeredUserId = UUID.fromString("93e89151-f034-4241-ba55-f5f95dbca92d");
    protected final String registeredUserAccount = "USER";
    protected final String registeredUserPassword = "1234";
    protected final String registeredEmail = "whrbql1@gmail.com";

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
        String content = objectMapper.writeValueAsString(userLoginRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.LOGIN)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

    }

    protected ResultActions register(String account, String password, String email) throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(account, password, email);
        String content = objectMapper.writeValueAsString(userRegisterRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.REGISTER)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    protected ResultActions addRole(String accessToken, UUID userId, RoleType roleType) throws Exception {
        UserAddRoleRequest userAddRoleRequest = new UserAddRoleRequest(RoleType.ADMIN);
        String content = objectMapper.writeValueAsString(userAddRoleRequest);

        return mockMvc.perform(MockMvcRequestBuilders.post(UserEndPointPath.ADD_ROLE, userId.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    protected ResultActions removeRole(String accessToken, UUID userId, RoleType roleType) throws Exception {
        UserAddRoleRequest userAddRoleRequest = new UserAddRoleRequest(RoleType.ADMIN);
        String content = objectMapper.writeValueAsString(userAddRoleRequest);

        return mockMvc.perform(MockMvcRequestBuilders.delete(UserEndPointPath.REMOVE_ROLE, userId.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }
}
