package com.example.honeyshop.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String id;
    private String password;
    private String nickname;
    private String phoneNumber;
}
