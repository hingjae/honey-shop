package com.example.honeyshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login() {
        return "login";
    }
}
