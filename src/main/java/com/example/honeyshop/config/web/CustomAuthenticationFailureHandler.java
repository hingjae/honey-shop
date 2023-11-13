package com.example.honeyshop.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.example.honeyshop.constant.SessionConstant.ERROR_MESSAGE_SESSION_KEY;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final String LOGIN_URL = "/login";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute(ERROR_MESSAGE_SESSION_KEY.name(), exception.getMessage());
        response.sendRedirect(LOGIN_URL);
    }
}
