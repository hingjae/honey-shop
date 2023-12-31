package com.example.honeyshop.service;

import com.example.honeyshop.dto.signup.SignUpRequest;
import com.example.honeyshop.entity.user.RoleType;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class SignUpServiceTest2 {

    @Autowired
    SignUpService signUpService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void before() {
        userRepository.deleteAll();

    }

    @DisplayName("signUp() : 회원가입 테스트")
    @Test
    void test() {
        //given
        SignUpRequest request = new SignUpRequest("id", "password", "nickname", "01012345678");

        //when
        String userId = signUpService.signUp(request);

        User user = userRepository.findById(userId).get();

        assertThat(user.getId()).isEqualTo(request.getId());
        assertThat(user.getNickname()).isEqualTo(request.getNickname());
        assertThat(user.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(user.getRoleTypes()).isEqualTo(Set.of(RoleType.USER));

    }
}
