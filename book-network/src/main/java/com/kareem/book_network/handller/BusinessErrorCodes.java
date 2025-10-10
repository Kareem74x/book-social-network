package com.kareem.book_network.handller;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {

    //The server doesn’t yet support this functionality.
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "no code"),

    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "User account is disabled"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "Login and / or Password is incorrect"),
    ;


    @Getter
    private int code;
    @Getter
    private String description;
    @Getter
    private HttpStatus httpStatus;


    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
