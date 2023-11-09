package com.example.honeyshop.service;

import com.example.honeyshop.dto.login.SignUpRequest;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks LoginService loginService;

    @Mock UserRepository userRepository;

    //1.이미 있는 아이디일 경우
    //2.정상적인 회원가입
    @DisplayName("이미 있는 아이디로 가입할 경우 예외를 던진다.")
    @Test
    void test() {
        //given
        User user = testUser();
        SignUpRequest request = new SignUpRequest("dupid", "password", "nickname", "1234");


    }

    private static User testUser() {
        return User.builder()
                .id("dupid")
                .password("password")
                .nickname("nickname")
                .phoneNumber("1234")
                .build();
    }
}