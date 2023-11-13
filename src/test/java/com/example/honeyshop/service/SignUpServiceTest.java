package com.example.honeyshop.service;

import com.example.honeyshop.dto.signup.SignUpRequest;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.exception.signup.DuplicateUserIdException;
import com.example.honeyshop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

    @InjectMocks
    SignUpService signUpService;

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    //1.이미 있는 아이디일 경우
    //2.정상적인 회원가입
    @DisplayName("signUp() : 이미 있는 아이디로 가입할 경우 예외를 던진다.")
    @Test
    void test() {
        //given
        User user = testUser();
        SignUpRequest request = new SignUpRequest("id", "password", "nickname", "1234");

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        Throwable t = Assertions.catchThrowable(() -> signUpService.signUp(request));

        assertThat(t)
                .isInstanceOf(DuplicateUserIdException.class)
                .hasMessage("이미 존재하는 아이디 입니다.");
        then(userRepository).should().findById(user.getId());

    }

    private static User testUser() {
        return User.builder()
                .id("id")
                .password("password")
                .nickname("nickname")
                .phoneNumber("1234")
                .build();
    }
}