package com.example.honeyshop.controller;

import com.example.honeyshop.constant.SessionConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/login")
@RequiredArgsConstructor
@Controller
public class LoginController {

    @GetMapping
    public String loginPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConstant.ERROR_MESSAGE_SESSION_KEY.name()) != null) {
            String errorMessage = String.valueOf(session.getAttribute(SessionConstant.ERROR_MESSAGE_SESSION_KEY.name()));
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute(SessionConstant.ERROR_MESSAGE_SESSION_KEY.name());
        }
        return "login";
    }

}
