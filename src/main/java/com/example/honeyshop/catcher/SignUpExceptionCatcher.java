package com.example.honeyshop.catcher;

import com.example.honeyshop.exception.signup.DuplicateUserIdException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SignUpExceptionCatcher {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserIdException.class)
    public String duplicateUserIdException(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "sign-up";
    }
}
