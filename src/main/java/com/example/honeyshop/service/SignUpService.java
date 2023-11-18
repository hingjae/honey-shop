package com.example.honeyshop.service;

import com.example.honeyshop.dto.signup.SignUpRequest;
import com.example.honeyshop.entity.user.RoleType;
import com.example.honeyshop.entity.user.User;
import com.example.honeyshop.exception.signup.DuplicateUserIdException;
import com.example.honeyshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signUp(SignUpRequest request) {
        userRepository.findById(request.getId())
                .ifPresent(user -> {
                    throw new DuplicateUserIdException();
                });

        String userId = createUser(request).getId();
        log.info("SignUp User : {}", userId);
        return userId;
    }

    private User createUser(SignUpRequest request) {
        return userRepository.save(User.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .phoneNumber(request.getPhoneNumber())
                .roleTypes(Set.of(RoleType.USER))
                .build()
        );
    }
}
