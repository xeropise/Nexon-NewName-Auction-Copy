package com.auction.user.controller;

import com.auction.user.test.UserSpringBootTestClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserRegisterControllerTest extends UserSpringBootTestClass {
    @Test
    public void user_register_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@gmail.com";

        reigster_first(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
