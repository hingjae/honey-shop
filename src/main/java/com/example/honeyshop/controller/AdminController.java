package com.example.honeyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping
    @ResponseBody
    public String adminPage() {
        return "ADMIN PAGE";
    }
}
