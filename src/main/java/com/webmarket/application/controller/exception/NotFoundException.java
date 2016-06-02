package com.webmarket.application.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Page not found")
class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
