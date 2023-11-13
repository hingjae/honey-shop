package com.example.honeyshop.controller;

import com.example.honeyshop.dto.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String home(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            log.info(userPrincipal.getUsername());
        }
        return "home";
    }
}
