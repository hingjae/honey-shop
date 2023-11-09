package com.example.honeyshop.exception.signup;

public class DuplicateUserIdException extends RuntimeException {
    private static final String MESSAGE = "이미 존재하는 아이디 입니다.";

    public DuplicateUserIdException() {
        super(MESSAGE);
    }
}
