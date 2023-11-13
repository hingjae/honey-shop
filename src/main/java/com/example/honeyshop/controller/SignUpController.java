package com.example.honeyshop.controller;

import com.example.honeyshop.dto.signup.SignUpRequest;
import com.example.honeyshop.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sign-up")
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String signUpPage() {
        return "sign-up";
    }

    // POST -> Redirect -> GET
    @PostMapping
    public String signUp(SignUpRequest request) {
        log.info(request.getId());
        signUpService.signUp(request);
        return "redirect:/";
    }
}
