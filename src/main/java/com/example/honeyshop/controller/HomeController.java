package com.example.honeyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @PostMapping
    @ResponseBody
    public String homePost() {
        return "homePost";
    }
}
