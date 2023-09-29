package com.auction.user.controller;

import com.auction.user.test.UserSpringBootTestClass;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class UserLoginControllerTest extends UserSpringBootTestClass {

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
}
