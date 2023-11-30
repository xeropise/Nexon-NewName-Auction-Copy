package com.auction.user.controller;

import com.auction.user.test.UserSpringBootTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserRegisterControllerTest extends UserSpringBootTestClass {
    @Test
    @DisplayName("유저 등록")
    public void user_register_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@gmail.com";

        register(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
