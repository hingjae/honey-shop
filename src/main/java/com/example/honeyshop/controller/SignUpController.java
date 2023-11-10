package com.example.honeyshop.controller;

import com.example.honeyshop.dto.login.SignUpRequest;
import com.example.honeyshop.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@RequestMapping("/sign-up")
@Controller
public class SignUpController {

    private final LoginService loginService;

    @GetMapping
    public String signUpPage() {
        return "sign-up";
    }

    // Post -> Redirect -> GET
    @PostMapping
    public String signUp(SignUpRequest request) {
        loginService.signUp(request);
        return "redirect:/";
    }
}
