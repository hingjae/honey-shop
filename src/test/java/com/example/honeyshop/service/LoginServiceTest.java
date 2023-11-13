package com.example.honeyshop.service;

import com.example.honeyshop.entity.user.RoleType;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    UserRepository userRepository;

    @DisplayName("loadUserByUsername() : 아이디를 찾는 데 성공한 경우")
    @Test
    void test() {
        User user = testUser();
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        UserDetails userDetails = loginService.loadUserByUsername(user.getId());

        assertThat(userDetails.getUsername()).isEqualTo(user.getId());
        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDetails.getAuthorities()).isEqualTo(user.getRoleTypes().stream()
                .map(RoleType::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableSet())
        );
        then(userRepository).should().findById(user.getId());
    }

    @DisplayName("loadUserByUsername() : 아이디를 찾는 데 실패한 경우")
    @Test
    void test2() {
        String userId = "id";
        given(userRepository.findById(userId)).willThrow(UsernameNotFoundException.class);

        Throwable t = catchThrowable(() -> loginService.loadUserByUsername(userId));

        assertThat(t)
                .isInstanceOf(UsernameNotFoundException.class);

        then(userRepository).should().findById(userId);
    }

    private static User testUser() {
        return User.builder()
                .id("id")
                .password("password")
                .nickname("nickname")
                .phoneNumber("1234")
                .roleTypes(Set.of(RoleType.USER))
                .build();
    }
}