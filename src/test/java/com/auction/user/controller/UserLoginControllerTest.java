package com.auction.user.controller;

import com.auction.user.test.UserSpringBootTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class UserLoginControllerTest extends UserSpringBootTestClass {

    @Test
    @DisplayName("유저 등록 후 로그인")
    public void user_register_login_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        register(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login(account, password).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("유저 등록 후 로그인 했지만 아이디 입력 안함")
    public void user_register_login_account_empty_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        register(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login("", password).andExpect(MockMvcResultMatchers.status().isBadRequest());
        login(null, password).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("유저 등록 후 로그인 했지만 패스워드 입력 안함")
    public void user_register_login_password_empty_test() throws Exception {
        String account = "xeropise";
        String password = "1234";
        String email = "whrbql1@naver.com";

        register(account, password, email).andExpect(MockMvcResultMatchers.status().isCreated());
        login(account, null).andExpect(MockMvcResultMatchers.status().isBadRequest());
        login(account, "").andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
